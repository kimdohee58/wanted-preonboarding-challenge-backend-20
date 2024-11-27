package com.wanted.wanted1.order.service.impl;

import com.wanted.wanted1.order.model.OrderDto;
import com.wanted.wanted1.order.model.OrderEntity;
import com.wanted.wanted1.order.repository.OrderRepository;
import com.wanted.wanted1.order.service.OrderService;
import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.product.model.Status;
import com.wanted.wanted1.product.repository.ProductRepository;
import com.wanted.wanted1.users.model.UserDetail;
import com.wanted.wanted1.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<OrderEntity>> findByUser(UserDetail userDetail) {
        return isUserAuthorized(userDetail)
                .map(authorized -> ResponseEntity.ok(orderRepository.findAllBySellerOrBuyer(userDetail.getUser().getId())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @Override
    public ResponseEntity<OrderEntity> findById(UserDetail userDetail, Long id) {
        return isUserAuthorized(userDetail)
                .map(authorized -> orderRepository.findById(id)
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @Override
    public ResponseEntity<OrderEntity> save(UserDetail userDetail, OrderDto order) {
        return isUserAuthorized(userDetail)
                .map(authorized -> {
                    OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
                            .orderNumber(order.getOrderNo())
                            .product(productRepository.findById(order.getProductId()).orElseThrow())
                            .seller(userRepository.findById(order.getSellerId()).orElseThrow())
                            .buyer(userRepository.findById(order.getBuyerId()).orElseThrow())
                            .build());

                    ProductEntity productEntity = productRepository.findById(order.getProductId()).orElseThrow();
                    productEntity.setStatus(Status.RESERVED);
                    productRepository.save(productEntity);

                    return ResponseEntity.ok(orderEntity);
                })
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    private Optional<UserDetail> isUserAuthorized(UserDetail userDetail) {
        return Optional.ofNullable(userDetail)
                .filter(detail -> !detail.getAuthorities().isEmpty());
    }
}
