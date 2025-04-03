package br.com.jeftavares.ecommerce.repository;

import br.com.jeftavares.ecommerce.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}