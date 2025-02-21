package br.com.jeftavares.ecommerce.repository;

import br.com.jeftavares.ecommerce.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}