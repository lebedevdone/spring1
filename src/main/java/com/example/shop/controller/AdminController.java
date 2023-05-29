package com.example.shop.controller;

import com.example.shop.entities.entity.Order;
import com.example.shop.entities.entity.Product;
import com.example.shop.entities.entity.Role;
import com.example.shop.entities.entity.User;
import com.example.shop.entities.entity.response.OrderDto;
import com.example.shop.service.order.OrderService;
import com.example.shop.service.product.ProductService;
import com.example.shop.service.role.RoleService;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping(value = "v1/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/find/product/all")
    public ResponseEntity<List<Product>> getProductList() {
        return ResponseEntity.ok(productService.findAllProductSorted());
    }

    @GetMapping("/order/find/all")
    public ResponseEntity<List<OrderDto>> getOrderList() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/order/find/{id}")
    public ResponseEntity<List<OrderDto>> getOrderList(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getOrderList(id));
    }

    @GetMapping("/role/find")
    public ResponseEntity<List<Role>> getRoleList() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/order/find")
    public ResponseEntity<List<OrderDto>> getOrderByLastCharacters(@RequestParam String characters) {
        return ResponseEntity.ok(orderService.findOrderByLastCharacters(characters));
    }

    @GetMapping("/product/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/product/update")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product/create")
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/order/update")
    public ResponseEntity<Void> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order/finds/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.findsOrderById(id));
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
