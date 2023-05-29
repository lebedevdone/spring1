package com.example.shop.repository;

import com.example.shop.entities.entity.Product;
import com.example.shop.entities.entity.User;
import com.example.shop.entities.entity.response.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE users u SET u.cart = :cart WHERE u.id = :userId")
    void addToCart(@Param("userId") Long userId, @Param("cart") Set<Product> cart);

    UserDto findUserDtoByUsername(String username);

    User findUserById(Long id);

}
