package com.example.shop.service.order;

import com.example.shop.entities.entity.Order;
import com.example.shop.entities.entity.response.OrderDto;
import com.example.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void updateOrder(Order order) {
        Order entity = orderRepository.findOrderEntityById(order.getId());
        entity.setOrderStatus(order.getOrderStatus());
        orderRepository.save(entity);
    }

    @Override
    public Order findOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    @Override
    public void deleteOrderById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto findsOrderById(String id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<OrderDto> findOrderByLastCharacters(String characters) {
        return orderRepository.findOrderDtoByLastFourCharacters(characters);
    }
}
