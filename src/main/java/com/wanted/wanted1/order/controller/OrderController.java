package com.wanted.wanted1.order.controller;

import com.wanted.wanted1.order.model.OrderDto;
import com.wanted.wanted1.order.model.OrderEntity;
import com.wanted.wanted1.order.service.OrderService;
import com.wanted.wanted1.users.model.UserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<OrderEntity>> findByUser(@AuthenticationPrincipal UserDetail userDetail) {
        return orderService.findByUser(userDetail);
    }

    @GetMapping("/{id}")
    @Operation(summary = "주문 상세보기", description = "주문 정보 및 상태 확인 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 조회 성공"),
            @ApiResponse(responseCode = "404", description = "주문 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(name = "id", description = "상세보기할 주문 id", example = "2")
    public ResponseEntity<OrderEntity> findById(@AuthenticationPrincipal UserDetail userDetail, @PathVariable Long id) {
        return orderService.findById(userDetail, id);
    }

    @PostMapping
    @Operation(summary = "구매하기 버튼을 통해 거래 시작", description = "구매자가 구매하기 버튼 눌렀을 때 호출되는 메서드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 생성 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Parameter(
            name = "order",
            description = "등록할 제품 정보",
            required = true,
            examples = @ExampleObject(
                    name = "exampleOrderDto",
                    value = """
                            {
                                "orderNo": "주문 번호",
                                "productId": "제품 id",
                                "sellerId": "판매자 id",
                                "buyerId": "구매자 id"
                            }
                            """
            ))
    public ResponseEntity<OrderEntity> save(@AuthenticationPrincipal UserDetail userDetail, @RequestBody OrderDto order) {
        return orderService.save(userDetail, order);
    }
}
