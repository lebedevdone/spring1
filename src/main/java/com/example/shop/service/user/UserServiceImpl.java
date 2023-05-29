package com.example.shop.service.user;

import com.example.shop.entities.entity.Order;
import com.example.shop.entities.entity.Product;
import com.example.shop.entities.entity.Role;
import com.example.shop.entities.entity.User;
import com.example.shop.entities.entity.request.UserRequestDto;
import com.example.shop.entities.entity.response.OrderDto;
import com.example.shop.entities.entity.response.UserDto;
import com.example.shop.exception.UsernameExistsException;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.RoleRepository;
import com.example.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RoleRepository roleRepository;

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void createUser(User user) {
        userRepository.findUserByUsername(user.getUsername()).ifPresent(x -> {
            throw new UsernameExistsException(String.format("Юзернейм %s уже занят", user.getUsername()));
        });
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByName("USER_ROLE");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        Hibernate.initialize(user.getOrder());
        Hibernate.initialize(user.getOrder());
        return user;
    }

    @Override
    public void updateUser(User user) {
        User oldUser = userRepository.findUserById(user.getId());
        oldUser.setRoles(user.getRoles());
        oldUser.setEmail(user.getEmail());
        oldUser.setName(user.getName());
        oldUser.setAddress(user.getAddress());
        userRepository.save(oldUser);
    }

    @Override
    public List<User> findAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }



    @Override
    public void clearCart(User user) {
        user.setCart(new HashSet<>());
    }

    @Override
    public void clearCart(String name) {
      User user = userRepository.findUserByUsername(name).orElseThrow();
      user.setCart(new HashSet<>());
      userRepository.save(user);
    }

    @Override
    public void addToCart(Long id, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        Product product = productRepository.findById(id).orElseThrow();
        Set<Product> set = new HashSet<>();
        set.add(product);
        user.getCart().addAll(set);
        userRepository.save(user);
    }

    @Override
    public void deleteProductFromCart(Long id, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        user.getCart().removeIf(p -> p.getId().equals(id));
        updateUser(user);
    }

    @Override
    public List<OrderDto> getOrderList(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public void createOrder(Order order, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setOrder(Set.of(order));
        user.getCart().clear();
        updateUser(user);
        orderRepository.save(order);
    }

    @Override
    public Set<Product> getCart(String name) {
        User user = userRepository.findUserByUsername(name).orElseThrow();
        return user.getCart();
    }

    @Override
    public void createOrderByName(String name) {
       User user = userRepository.findUserByUsername(name).orElseThrow();
       Order order = new Order(user);
       orderRepository.save(order);
       clearCart(user);
    }

    @Override
    public List<OrderDto> findOrders(String name) {
        User user = userRepository.findUserByUsername(name).orElseThrow();
        return orderRepository.findAllByUserId(user.getId());
    }

    @Override
    public UserDto findBaseInfo(String name) {
        return userRepository.findUserDtoByUsername(name);
    }

    @Override
    public void updateUserInfo(UserRequestDto userRequestDto, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setAddress(userRequestDto.getAddress());
        userRepository.save(user);
    }
}
