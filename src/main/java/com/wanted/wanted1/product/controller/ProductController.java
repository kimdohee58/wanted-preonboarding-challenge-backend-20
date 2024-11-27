package com.wanted.wanted1.product.controller;

import com.wanted.wanted1.product.model.ProductDto;
import com.wanted.wanted1.product.model.ProductEntity;
import com.wanted.wanted1.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "등록된 목록", description = "등록되어있는 전체 제품 목록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "제품 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "제품 목록 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<ProductEntity>> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "제품 상세보기", description = "제품 정보 및 상태 확인 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "제품 조회 성공"),
            @ApiResponse(responseCode = "404", description = "제품 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(name = "제품 id", description = "상세보기할 제품 id", example = "2")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // todo 내가 등록한 상품 리스트 보여주기?
    @GetMapping("/findByUser")
    public ResponseEntity<List<ProductEntity>> findByUser(Long id) {
        return productService.findByUser(id);
    }

    @PostMapping
    @Operation(summary = "제품 등록", description = "회원이 제품 등록할 때 호출될 메서드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "제품 등록 성공"),
            @ApiResponse(responseCode = "404", description = "찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(examples = {
            @ExampleObject(name = "exampleProductDto", value = """ 
                        { 
                            "name" : "제품 이름",
                            "price" : "제품 가격", 
                            "seller" : "판매자 email"
                        } 
                    """)})
    public ResponseEntity<ProductEntity> save(ProductDto product) {
        return productService.save(product);
    }

    @PatchMapping
    @Operation(summary = "제품 상태 수정", description = "등록된 제품의 상태를 수정할 때 호출하는 메서드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(examples = {
            @ExampleObject(name = "exampleProductDto", value = """ 
                        { 
                            "id" : "제품 id",
                            "seller" : "판매자 email"
                        } 
                    """)})
    public ResponseEntity<ProductEntity> update(ProductDto product) {
        return productService.update(product);
    }
}
