package com.wanted.wanted1.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private BigDecimal price;
    private Status status;

    private Long seller;
}
