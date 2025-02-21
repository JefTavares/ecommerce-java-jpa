package br.com.jeftavares.ecommerce.controller.dto;

public record CreateUserDto(String fullName,
                            String address,
                            String number,
                            String complement) {
}