package com.example.shop.repository;

import com.example.shop.entities.entity.Order;
import com.example.shop.entities.entity.response.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o.id as id, o.user.name as name, o.user.address as address, SUM(p.price) as price, o.orderStatus as status, " +
            "string_agg(p.name, ',') as productName " +
            "FROM orders o " +
            "JOIN o.productSet p " +
            "WHERE o.user.id = :userId " +
            "GROUP BY o.id, o.user.name, o.user.address, o.orderStatus")
    List<OrderDto> findAllByUserId(Long userId);

    @Query("SELECT o.id as id, o.user.name as name, o.user.address as address, SUM(p.price) as price, o.orderStatus as status, " +
            "string_agg(p.name, ',') as productName " +
            "FROM orders o " +
            "JOIN o.productSet p " +
            "GROUP BY o.id, o.user.name, o.user.address, o.orderStatus")
    List<OrderDto> findAllOrders();

    @Query("SELECT o.id as id, o.user.name as name, o.user.address as address, SUM(p.price) as price, o.orderStatus as status, " +
            "string_agg(p.name, ',') as productName " +
            "FROM orders o " +
            "JOIN o.productSet p " +
            "WHERE o.id = :id " +
            "GROUP BY o.id, o.user.name, o.user.address, o.orderStatus")
    OrderDto findOrderById(String id);



    @Query("SELECT o.id as id, o.user.name as name, o.user.address as address, SUM(p.price) as price, o.orderStatus as status, " +
            "string_agg(p.name, ',') as productName " +
            "FROM orders o " +
            "JOIN o.productSet p " +
            "WHERE o.id LIKE CONCAT('%', :lastFourCharacters) " +
            "GROUP BY o.id, o.user.name, o.user.address, o.orderStatus")
    List<OrderDto> findOrderDtoByLastFourCharacters(@Param("lastFourCharacters") String lastFourCharacters);


    @Query("SELECT p FROM orders p WHERE p.id = :id")
    Order findOrderEntityById(String id);



}
