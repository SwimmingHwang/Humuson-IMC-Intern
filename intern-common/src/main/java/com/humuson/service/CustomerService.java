/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.humuson.service;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.dto.CustomerListResponseDto;
import com.humuson.dto.CustomerResponseDto;
import com.humuson.dto.CustomerSaveRequestDto;
import com.humuson.dto.CustomerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public long save(CustomerSaveRequestDto requestDto) {
        return customerRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

    @Transactional
    public long update(long id, CustomerUpdateRequestDto requestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        customer.update(requestDto.getUserId(), requestDto.getName(), requestDto.getPhoneNumber());

        return id;
    }

    @Transactional
    public void delete (long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        customerRepository.delete(customer);
    }

    @Transactional(readOnly = true)
    public CustomerResponseDto findById(long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new CustomerResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CustomerListResponseDto> findAllDesc() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return customerRepository.findAllDesc().stream()
                .map(CustomerListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return customerRepository.findAll();
    }
}