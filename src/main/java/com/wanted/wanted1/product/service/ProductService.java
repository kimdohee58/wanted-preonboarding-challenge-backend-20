package com.wanted.wanted1.product.service;

import com.wanted.wanted1.product.model.ProductDto;
import com.wanted.wanted1.product.model.ProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<List<ProductEntity>> findAll();
    ResponseEntity<ProductEntity> findById(Long id);
    ResponseEntity<List<ProductEntity>> findByUser(Long id);
    ResponseEntity<ProductEntity> save(ProductDto product);
    ResponseEntity<ProductEntity> update(ProductDto product);
}
