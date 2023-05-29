package com.example.shop.controller;

import com.example.shop.entities.entity.Product;
import com.example.shop.entities.entity.User;
import com.example.shop.entities.entity.request.UserRequestDto;
import com.example.shop.entities.entity.response.OrderDto;
import com.example.shop.entities.entity.response.UserDto;
import com.example.shop.exception.Response;
import com.example.shop.exception.UsernameExistsException;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("v2/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<User> getInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.findUserByUsername(userDetails.getUsername()));
    }

    @GetMapping("/base/info")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserDto> getBaseInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.findBaseInfo(userDetails.getUsername()));
    }

    @PutMapping("/common/cart/add/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> addToCart(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        userService.addToCart(id, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/common/cart/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Set<Product>> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getCart(userDetails.getUsername()));
    }

    @PostMapping("/order/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> createOrder(@AuthenticationPrincipal UserDetails userDetails) {
        userService.createOrderByName(userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order/find/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<OrderDto>> getOrderList(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.findOrders(userDetails.getUsername()));
    }

    @DeleteMapping("/cart/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<OrderDto>> removeProductFromCart(@PathVariable Long id,
                                                                @AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteProductFromCart(id, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public Response registration(@RequestBody User user) {
        userService.createUser(user);
        return new Response("Регистрация завершена", true);
    }

    @PostMapping("/update")
    public Response updateUserInfo(@RequestBody UserRequestDto userRequestDto,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        userService.updateUserInfo(userRequestDto,userDetails.getUsername());
        return new Response("Информация обновлена", true);
    }

    @ExceptionHandler({UsernameExistsException.class})
    public Response handleException(UsernameExistsException e) {
        return new Response(e.getMessage(), false);
    }
}
