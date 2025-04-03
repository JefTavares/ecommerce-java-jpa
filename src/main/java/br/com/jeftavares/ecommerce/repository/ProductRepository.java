package br.com.jeftavares.ecommerce.repository;

import br.com.jeftavares.ecommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}