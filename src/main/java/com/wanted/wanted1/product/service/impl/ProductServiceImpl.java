package com.wanted.wanted1.product.service.impl;

import com.wanted.wanted1.product.model.ProductDto;
import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.product.model.Status;
import com.wanted.wanted1.product.repository.ProductRepository;
import com.wanted.wanted1.product.service.ProductService;
import com.wanted.wanted1.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<ProductEntity>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @Override
    public ResponseEntity<ProductEntity> findById(Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<ProductEntity>> findByUser(Long id) {
        return ResponseEntity.ok(productRepository.findBySeller(id));
    }

    @Override
    public ResponseEntity<ProductEntity> save(ProductDto product) {
        return userRepository.findById(product.getSeller())
                .map(user -> {
                    ProductEntity productEntity = ProductEntity.builder()
                            .name(product.getName())
                            .price(product.getPrice())
                            .seller(user)
                            .build();
                    ProductEntity savedProduct = productRepository.save(productEntity);
                    return ResponseEntity.ok(savedProduct);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<ProductEntity> update(ProductDto product) {
        return productRepository.findById(product.getId())
                .map(existingProduct -> {
                    existingProduct.setStatus(Status.COMPLETED);
                    ProductEntity updatedProduct = productRepository.save(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
