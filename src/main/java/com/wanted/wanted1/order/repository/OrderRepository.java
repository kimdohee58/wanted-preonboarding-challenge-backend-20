package com.wanted.wanted1.order.repository;

import com.wanted.wanted1.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllBySellerOrBuyer(Long userId);
}
