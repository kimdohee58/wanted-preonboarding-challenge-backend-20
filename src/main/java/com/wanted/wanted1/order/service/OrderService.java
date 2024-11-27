package com.wanted.wanted1.order.service;

import com.wanted.wanted1.order.model.OrderDto;
import com.wanted.wanted1.order.model.OrderEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<List<OrderEntity>> findByUser(Long id);

    ResponseEntity<OrderEntity> findById(Long id);

    ResponseEntity<OrderEntity> save(OrderDto order);
}
