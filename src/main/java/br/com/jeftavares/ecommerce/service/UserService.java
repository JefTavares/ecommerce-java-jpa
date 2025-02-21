package br.com.jeftavares.ecommerce.service;

import br.com.jeftavares.ecommerce.controller.dto.CreateUserDto;
import br.com.jeftavares.ecommerce.entities.BillingAddressEntity;
import br.com.jeftavares.ecommerce.entities.UserEntity;
import br.com.jeftavares.ecommerce.repository.BillingAddressRepository;
import br.com.jeftavares.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

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

        var savedBillingAddress = billingAddressRepository.save(billingAddress);

        UserEntity user = new UserEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddress(savedBillingAddress);

        return userRepository.save(user);
    }
}