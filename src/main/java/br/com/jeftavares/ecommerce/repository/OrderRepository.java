package br.com.jeftavares.ecommerce.repository;

import br.com.jeftavares.ecommerce.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}