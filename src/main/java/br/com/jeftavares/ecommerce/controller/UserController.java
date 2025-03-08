package br.com.jeftavares.ecommerce.controller;

import br.com.jeftavares.ecommerce.controller.dto.CreateUserDto;
import br.com.jeftavares.ecommerce.entities.UserEntity;
import br.com.jeftavares.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {

        var user = userService.createUser(dto);

        return ResponseEntity.created(URI.create("/users/" + user.getUserId())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("userId") UUID userId) {

        var user = userService.findById(userId);

        //Sugestão da IDE
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        /*        return user.isPresent() ?
                        ResponseEntity.ok(user.get()) :
                        ResponseEntity.notFound().build(); */

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") UUID userId) {

        var deleted = userService.deleteById(userId);

        //Sugestão da IDE
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();


    }



    }