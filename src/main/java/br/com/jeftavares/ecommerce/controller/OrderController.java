package br.com.jeftavares.ecommerce.controller;

import br.com.jeftavares.ecommerce.controller.dto.*;
import br.com.jeftavares.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDto dto) {
        var order = orderService.createOrder(dto);
        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<OrderSummaryDto>> listOrders(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var resp = orderService.findAll(page, pageSize);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        resp.getContent(),
                        new PaginationResponseDto(
                                resp.getNumber(),
                                resp.getSize(),
                                resp.getTotalElements(),
                                resp.getTotalPages())
                ));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable("orderId") Long orderId) {

        var order = orderService.findById(orderId);

        return order.isPresent() ?
                ResponseEntity.ok(OrderResponseDto.fromEntity(order.get())) :
                ResponseEntity.notFound().build();
    }
}