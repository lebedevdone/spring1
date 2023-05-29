package com.example.shop.service.product;

import com.example.shop.entities.entity.Product;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    @Transactional
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addAll(List<Product> list) {
        productRepository.saveAll(list);
    }

    @Override
    public Page<Product> findProducts(int page, int size, String sort) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort)));
    }

    @Override
    public Page<Product> findProductContainsName(String name) {
        return  productRepository.findAllProductsContainsName(name, PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "name")));
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProductSorted() {
        return productRepository.findAllProductSorted();
    }
}
