package com.example.main.controller;

import com.example.main.dto.CustomerListResponseDto;
import com.example.main.dto.CustomerResponseDto;
import com.example.main.dto.CustomerSaveRequestDto;
import com.example.main.dto.CustomerUpdateRequestDto;
import com.example.main.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping("/api/v1/customer")
    public long save(@RequestBody CustomerSaveRequestDto requestDto) {
        return customerService.save(requestDto);
    }

    @PutMapping("/api/v1/customer/{id}")
    public long update(@PathVariable long id, @RequestBody CustomerUpdateRequestDto requestDto) {
        return customerService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/customer/{id}")
    public long delete(@PathVariable long id) {
        customerService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/customer/{id}")
    public CustomerResponseDto findById(@PathVariable long id) {
        return customerService.findById(id);
    }

    @GetMapping("/api/v1/customer/list")
    public List<CustomerListResponseDto> findAll() {
        return customerService.findAllDesc();
    }
}