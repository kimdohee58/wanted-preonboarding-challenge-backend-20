package com.wanted.wanted1.product.service.impl;

import com.wanted.wanted1.product.model.ProductDto;
import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.product.model.Status;
import com.wanted.wanted1.product.repository.ProductRepository;
import com.wanted.wanted1.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductEntity>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @Override
    public ResponseEntity<ProductEntity> findById(Long id) {
        return ResponseEntity.ok(productRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<List<ProductEntity>> findByUser(Long id) {
        return ResponseEntity.ok(productRepository.findBySeller(id));
    }

    @Override
    public ResponseEntity<ProductEntity> save(ProductDto product) {
        return ResponseEntity.ok(productRepository.save(ProductEntity.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .seller(product.getSeller())
                .build()));
    }

    @Override
    public ResponseEntity<ProductEntity> update(ProductDto product) {
        return productRepository.findById(product.getId())
                .filter(t-> productRepository.existsById(product.getId()))
                .map(t-> {
                    ProductEntity productEntity = productRepository.findById(product.getId()).get();
                    productEntity.setStatus(Status.COMPLETED);
                    return new ResponseEntity<>(productEntity, HttpStatus.OK);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
