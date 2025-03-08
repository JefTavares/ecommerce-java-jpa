package br.com.jeftavares.ecommerce.service;

import br.com.jeftavares.ecommerce.controller.dto.CreateUserDto;
import br.com.jeftavares.ecommerce.entities.BillingAddressEntity;
import br.com.jeftavares.ecommerce.entities.UserEntity;
import br.com.jeftavares.ecommerce.repository.BillingAddressRepository;
import br.com.jeftavares.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDto dto) {

        var billingAddress = new BillingAddressEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setNumber(dto.number());
        billingAddress.setComplement(dto.complement());

        //Como o UserEntity esta com @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL),
        // ele ja salva o BillingAddressEntity na operação de save do UserEntity
        //var savedBillingAddress = billingAddressRepository.save(billingAddress);

        UserEntity user = new UserEntity();
        user.setFullName(dto.fullName());
        //user.setBillingAddress(savedBillingAddress);
        user.setBillingAddress(billingAddress);

        return userRepository.save(user);
    }

    public Optional<UserEntity> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Boolean deleteById(UUID userId) {

        var user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.deleteById(userId);
            //Como o UserEntity esta com @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL),
            // ele ja salva o BillingAddressEntity na operação de save do UserEntity
            // billingAddressRepository.deleteById(user.get().getBillingAddress().getBillingAddressId());
        }

        return user.isPresent();

    }
}