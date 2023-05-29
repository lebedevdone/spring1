package com.example.shop.service.user;

import com.example.shop.entities.entity.Order;
import com.example.shop.entities.entity.Product;
import com.example.shop.entities.entity.User;
import com.example.shop.entities.entity.request.UserRequestDto;
import com.example.shop.entities.entity.response.OrderDto;
import com.example.shop.entities.entity.response.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    void delete(User user);
    void createUser(User user);
    User findUserByUsername(String username);
    void updateUser(User user);
    List<User> findAllUsers();
    void deleteUserById(Long id);
    User findUserById(Long id);
    void clearCart(String username);
    void clearCart(User user);
    void addToCart(Long id, String username);
    void deleteProductFromCart(Long id, String username);
    List<OrderDto> getOrderList(Long userId);
    void createOrder(Order order, Long id);
    Set<Product> getCart(String name);
    void createOrderByName(String name);
    List<OrderDto> findOrders(String name);
    UserDto findBaseInfo(String name);
    void updateUserInfo(UserRequestDto user, String username);
}
