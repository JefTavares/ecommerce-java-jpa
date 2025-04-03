package br.com.jeftavares.ecommerce.controller.dto;

public record OrderItemDto(Integer quantity,
                           Long productId){}