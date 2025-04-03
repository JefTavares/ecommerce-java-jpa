package br.com.jeftavares.ecommerce.controller.dto;

import br.com.jeftavares.ecommerce.entities.TagEntity;

public record TagResponseDto(Long tagId,
                             String name) {

    public static TagResponseDto fromEntity(TagEntity tagEntity) {
        return new TagResponseDto(
                tagEntity.getTagId(),
                tagEntity.getName()
        );
    }
}