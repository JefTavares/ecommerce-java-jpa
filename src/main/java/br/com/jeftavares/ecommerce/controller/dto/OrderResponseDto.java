package br.com.jeftavares.ecommerce.controller.dto;

import br.com.jeftavares.ecommerce.entities.OrderEntity;
import br.com.jeftavares.ecommerce.entities.OrderItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(Long orderId,
                               BigDecimal total,
                               LocalDateTime orderDate,
                               UUID userId,
                               List<OrderItemResponseDto> items) {

    public static OrderResponseDto fromEntity(OrderEntity entity) {
        //faz toda a lógica de conversão da entity para o dto
        return new OrderResponseDto(
                entity.getOrderId(),
                entity.getTotal(),
                entity.getOrderDate(),
                entity.getUser().getUserId(),
                getItems(entity.getItems())
        );
    }

    private static List<OrderItemResponseDto> getItems(List<OrderItemEntity> items) {
        return items.stream()
                .map(OrderItemResponseDto::fromEntity)
                .toList();
    }
}