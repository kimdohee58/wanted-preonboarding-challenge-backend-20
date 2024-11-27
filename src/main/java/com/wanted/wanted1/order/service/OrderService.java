package com.wanted.wanted1.order.service;

import com.wanted.wanted1.order.model.OrderDto;
import com.wanted.wanted1.order.model.OrderEntity;
import com.wanted.wanted1.users.model.UserDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<List<OrderEntity>> findByUser(UserDetail userDetail);

    ResponseEntity<OrderEntity> findById(UserDetail userDetail, Long id);

    ResponseEntity<OrderEntity> save(UserDetail userDetail, OrderDto order);
}
