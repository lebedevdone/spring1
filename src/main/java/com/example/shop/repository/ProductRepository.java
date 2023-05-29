package com.example.shop.repository;

import com.example.shop.entities.entity.Product;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List<Product> findAllByName(String name);

    void deleteProductById(Long id);

    Page<Product> findAll(@NonNull Pageable pageable);

    @Query("SELECT p FROM products p ORDER BY p.id")
    List<Product> findAllProductSorted();

    @Query("SELECT p FROM products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Product> findAllProductsContainsName(@Param("name") String name, Pageable pageable);

}
