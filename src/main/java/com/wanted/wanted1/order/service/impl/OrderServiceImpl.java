package com.wanted.wanted1.order.service.impl;

import com.wanted.wanted1.order.model.OrderDto;
import com.wanted.wanted1.order.model.OrderEntity;
import com.wanted.wanted1.order.repository.OrderRepository;
import com.wanted.wanted1.order.service.OrderService;
import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.product.model.Status;
import com.wanted.wanted1.product.repository.ProductRepository;
import com.wanted.wanted1.users.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<OrderEntity>> findByUser(Long id) {
        return ResponseEntity.ok(orderRepository.findAllBySellerOrBuyer(id));
    }

    @Override
    public ResponseEntity<OrderEntity> findById(Long id) {
        return ResponseEntity.ok(orderRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<OrderEntity> save(OrderDto order) {
        // TODO : product status 예약중으로 변경
        OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
                .orderNumber(order.getOrderNo())
                .product(productRepository.findById(order.getId()).get())
                .seller(userRepository.findById(order.getSellerId()).get())
                .buyer(userRepository.findById(order.getBuyerId()).get())
                .build());

        if (orderEntity != null) {
            ProductEntity productEntity = productRepository.findById(order.getProductId()).get();
            productEntity.setStatus(Status.RESERVED);
            productRepository.save(productEntity);
            return ResponseEntity.ok(orderEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
