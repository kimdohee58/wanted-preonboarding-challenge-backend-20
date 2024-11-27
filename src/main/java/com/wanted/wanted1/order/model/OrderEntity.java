package com.wanted.wanted1.order.model;

import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.users.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @JoinColumn(name = "product_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductEntity product;

    @JoinColumn(name = "seller_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserEntity seller;

    @JoinColumn(name = "buyer_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserEntity buyer;
}
