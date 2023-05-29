package com.example.shop.controller;

import com.example.shop.entities.entity.User;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("v2/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;

    @GetMapping("/current")
    public ResponseEntity<User> getUserCart(Principal principal) {
        return ResponseEntity.ok(userService.findUserByUsername(principal.getName()));
    }
}
