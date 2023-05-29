package com.example.shop.entities.entity.response;

import com.example.shop.entities.enums.OrderStatus;

import java.util.List;

public interface OrderDto {
    String getId();
    String getName();
    String getAddress();
    Double getPrice();
    List<String> getProductName();
    OrderStatus getStatus();
}