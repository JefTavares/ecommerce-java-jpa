package br.com.jeftavares.ecommerce.service;

import br.com.jeftavares.ecommerce.controller.dto.CreateOrderDto;
import br.com.jeftavares.ecommerce.controller.dto.OrderItemDto;
import br.com.jeftavares.ecommerce.controller.dto.OrderSummaryDto;
import br.com.jeftavares.ecommerce.entities.*;
import br.com.jeftavares.ecommerce.exception.CreateOrderException;
import br.com.jeftavares.ecommerce.repository.OrderRepository;
import br.com.jeftavares.ecommerce.repository.ProductRepository;
import br.com.jeftavares.ecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderEntity createOrder(CreateOrderDto dto) {

        var order = new OrderEntity();

        // 1. Validate User
        var user = validateUser(dto);

        // 2. Validate Order Items
        var orderItems = validateOrderItems(order, dto);

        // 3. Calculate order total
        var total = calculateOrderTotal(orderItems);

        // 4. Map to Entity
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setItems(orderItems);
        order.setTotal(total);

        // 5. Repository Save
        return orderRepository.save(order);
    }

    //Faz uma lógica de validação do usuário
    private UserEntity validateUser(CreateOrderDto dto) {
        return userRepository.findById(dto.userId())
                .orElseThrow(() -> new CreateOrderException("User not found"));
    }

    private List<OrderItemEntity> validateOrderItems(OrderEntity order,
                                                     CreateOrderDto dto) {
        if (dto.items().isEmpty()) {
            throw new CreateOrderException("Order items is empty");
        }

        return dto.items().stream()
                .map(orderItemDto -> getOrderItem(order, orderItemDto))
                .toList();
    }

    private OrderItemEntity getOrderItem(OrderEntity order, OrderItemDto orderItemDto) {
        var orderItemEntity = new OrderItemEntity();
        var id = new OrderItemId();
        var product = getProduct(orderItemDto.productId());

        id.setOrder(order);
        id.setProduct(product);

        orderItemEntity.setId(id);
        orderItemEntity.setQuantity(orderItemDto.quantity());
        orderItemEntity.setSalePrice(product.getPrice());

        return orderItemEntity;
    }

    private ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CreateOrderException("Product not found"));
    }

    private BigDecimal calculateOrderTotal(List<OrderItemEntity> items) {
        //para cada item estamos multiplicando o valor pela quantidade
        //e depois somando todos os valores
        return items.stream()
                .map(i -> i.getSalePrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


    public Page<OrderSummaryDto> findAll(Integer page, Integer pageSize) {
        return orderRepository.findAll(PageRequest.of(page, pageSize))
                .map(entity -> new OrderSummaryDto(
                                entity.getOrderId(),
                                entity.getOrderDate(),
                                entity.getUser().getUserId(),
                                entity.getTotal()
                        )
                );
    }

    public Optional<OrderEntity> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}