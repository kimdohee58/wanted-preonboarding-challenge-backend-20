package com.wanted.wanted1.product.service;

import com.wanted.wanted1.product.model.ProductDto;
import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.users.model.UserDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<List<ProductEntity>> findAll();
    ResponseEntity<ProductEntity> findById(Long id);
    ResponseEntity<List<ProductEntity>> findByUser(UserDetail userDetail, Long id);
    ResponseEntity<ProductEntity> save(UserDetail userDetail, ProductDto product);
    ResponseEntity<ProductEntity> update(UserDetail userDetail, ProductDto product);
}
