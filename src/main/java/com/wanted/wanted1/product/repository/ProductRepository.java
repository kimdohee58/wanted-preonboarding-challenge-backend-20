package com.wanted.wanted1.product.repository;

import com.wanted.wanted1.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public List<ProductEntity> findBySeller(Long id);
}
