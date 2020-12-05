package com.humuson.controller;

import com.humuson.domain.entity.Customer;
import com.humuson.dto.customer.CustomerListResponseDto;
import com.humuson.dto.customer.CustomerResponseDto;
import com.humuson.dto.customer.CustomerSaveRequestDto;
import com.humuson.dto.customer.CustomerUpdateRequestDto;
import com.humuson.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="고객 관리", description = "고객 주소록의 정보를 관리합니다.")
@RequiredArgsConstructor
@RestController
public class CustomerApiController {
    private final CustomerService customerService;


    @Operation(summary="고객 생성", description = "고객 주소록에 고객 정보를 추가")
    @PostMapping("/api/v1/customer")
    public long save(@RequestBody CustomerSaveRequestDto requestDto) {
        return customerService.save(requestDto);
    }

    @Operation(summary="고객 수정", description = "고객 주소록의 고객 정보를 수정")
    @PutMapping("/api/v1/customer/{id}")
    public long update(@PathVariable long id, @RequestBody CustomerUpdateRequestDto requestDto) {
        return customerService.update(id, requestDto);
    }
    @Operation(summary="고객 삭제", description = "고객 주소록의 고객 정보를 삭제")
    @DeleteMapping("/api/v1/customer/{id}")
    public long delete(@PathVariable long id) {
        customerService.delete(id);
        return id;
    }
    @Operation(summary="고객 조회", description = "고객 주소록의 고객 정보를 조희")
    @GetMapping("/api/v1/customer/{id}")
    public Customer findById(@PathVariable long id) {
        return customerService.findById(id);
    }

    @Operation(summary="고객 리스트 조회", description = "고객 주소록의 고객 정보들을 조희")
    @GetMapping("/api/v1/customer/list")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @Operation(summary = "고객 그룹 정보 업데이트", description = "고객의 그룹 정보를 업데이트 함.")
    @PostMapping("api/v1/customer/group-info-update")
    public String groupInfoUpdate(List<String> idList){
        // 무조건 insert

        return "200";
    }
}