package com.humuson.controller;

import com.humuson.dto.customer.CustomerGroupListResponseDto;
import com.humuson.dto.customer.CustomerGroupResponseDto;
import com.humuson.dto.customer.CustomerGroupSaveRequestDto;
import com.humuson.dto.customer.CustomerGroupUpdateRequestDto;
import com.humuson.service.CustomerGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="고객 관리", description = "고객 주소록의 정보를 관리합니다.")
@RequiredArgsConstructor
@RestController
public class CustomerGroupApiController {
    private final CustomerGroupService customerGroupService;


    @Operation(summary="고객 생성", description = "고객 주소록에 고객 정보를 추가")
    @PostMapping("/api/v1/customer/group")
    public long save(@RequestBody CustomerGroupSaveRequestDto requestDto) {
        return customerGroupService.save(requestDto);
    }

    @Operation(summary="고객 수정", description = "고객 주소록의 고객 정보를 수정")
    @PutMapping("/api/v1/customer/group/{id}")
    public long update(@PathVariable long id, @RequestBody CustomerGroupUpdateRequestDto requestDto) {
        return customerGroupService.update(id, requestDto);
    }
    @Operation(summary="고객 삭제", description = "고객 주소록의 고객 정보를 삭제")
    @DeleteMapping("/api/v1/customer/group/{id}")
    public long delete(@PathVariable long id) {
        customerGroupService.delete(id);
        return id;
    }
    @Operation(summary="고객 조회", description = "고객 주소록의 고객 정보를 조희")
    @GetMapping("/api/v1/customer/group/{id}")
    public CustomerGroupResponseDto findById(@PathVariable long id) {
        return customerGroupService.findById(id);
    }
    @Operation(summary="고객 리스트 조회", description = "고객 주소록의 고객 정보들을 조희")
    @GetMapping("/api/v1/customer/group/list")
    public List<CustomerGroupListResponseDto> findAll() {
        return customerGroupService.findAllDesc();
    }
}