package com.wanted.wanted1.order.controller;

import com.wanted.wanted1.order.model.OrderDto;
import com.wanted.wanted1.order.model.OrderEntity;
import com.wanted.wanted1.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "orders", description = "Order Controller")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "사용자 기준 주문 목록", description = "사용자 기준 구매/예약 중인 용품 주문 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(name = "로그인한 사용자 id", description = "조회할 사용자의 id", example = "2")
    public ResponseEntity<List<OrderEntity>> findByUser(Long id) {
        return orderService.findByUser(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "주문 상세보기", description = "주문 정보 및 상태 확인 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 조회 성공"),
            @ApiResponse(responseCode = "404", description = "주문 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(name = "주문 id", description = "상세보기할 주문 id", example = "2")
    public ResponseEntity<OrderEntity> findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @Operation(summary = "구매하기 버튼을 통해 거래 시작", description = "구매자가 구매하기 버튼 눌렀을 때 호출되는 메서드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(examples = {
            @ExampleObject(name = "exampleOrderModel", value = """ 
                        { 
                            "orderNo" : "주문 번호",
                            "productId" : "제품 id", 
                            "sellerId" : "판매자 id", 
                            "buyerId" : "구매자 id"
                        } 
                    """)})
    public ResponseEntity<OrderEntity> save(@RequestBody OrderDto order) {
        return orderService.save(order);
    }
}
