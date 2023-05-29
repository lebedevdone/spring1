package com.example.shop.service.order;

import com.example.shop.entities.entity.Order;
import com.example.shop.entities.entity.response.OrderDto;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    void deleteOrder(Order order);
    void updateOrder(Order order);
    Order findOrderById(String id);
    List<OrderDto> findAllOrders();
    void deleteOrderById(String id);
    OrderDto findsOrderById(String id);
    List<OrderDto> findOrderByLastCharacters(String characters);
}
