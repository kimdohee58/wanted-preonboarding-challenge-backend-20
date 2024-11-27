package com.wanted.wanted1.product.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private Status status;
    private Long seller;
}
