package com.example.shop.service.product;

import com.example.shop.entities.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> getProductsByName(String name);

    void createProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findAllProducts();

    void addAll(List<Product> list);

    Page<Product> findProducts(int page, int size, String sort);

    Page<Product> findProductContainsName(String name);

    void updateProduct(Product product);

    List<Product> findAllProductSorted();
}
