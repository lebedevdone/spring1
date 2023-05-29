package com.example.shop.entities.entity;

import com.example.shop.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id = RandomStringUtils.randomAlphanumeric(10);

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet = new HashSet<>();

    private OrderStatus orderStatus = OrderStatus.NEW;

    @ManyToOne
    private User user;

    public Order(User user) {
        this.user = user;
        this.productSet = user.getCart();
    }

    public Order(String id, OrderStatus orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }
}
