package br.com.jeftavares.ecommerce.repository;

import br.com.jeftavares.ecommerce.entities.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, Long> {
}