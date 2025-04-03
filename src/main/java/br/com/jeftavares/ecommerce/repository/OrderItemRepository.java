package br.com.jeftavares.ecommerce.repository;

import br.com.jeftavares.ecommerce.entities.OrderItemEntity;
import br.com.jeftavares.ecommerce.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}