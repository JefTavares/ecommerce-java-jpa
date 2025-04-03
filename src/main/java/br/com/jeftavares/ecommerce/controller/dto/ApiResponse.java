package br.com.jeftavares.ecommerce.controller.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponseDto pagination) {
}