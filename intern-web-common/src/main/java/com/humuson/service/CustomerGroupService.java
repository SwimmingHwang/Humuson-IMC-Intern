package com.humuson.service;

import com.humuson.domain.entity.CustomerGroup;
import com.humuson.domain.repository.CustomerGroupRepository;
import com.humuson.dto.customer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerGroupService {
    private final CustomerGroupRepository customerGroupRepository;

    @Transactional
    public long save(CustomerGroupSaveRequestDto requestDto) {
        return customerGroupRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

    @Transactional
    public long update(long id, CustomerGroupUpdateRequestDto requestDto) {
        CustomerGroup customerGroup = customerGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        customerGroup.updateGroupName(requestDto.getGroupName());

        return id;
    }

    @Transactional
    public void delete (long id) {
        CustomerGroup customerGroup = customerGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        customerGroupRepository.delete(customerGroup);
    }

    @Transactional(readOnly = true)
    public CustomerGroupResponseDto findById(long id) {
        CustomerGroup entity = customerGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new CustomerGroupResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public List<CustomerGroup> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return customerGroupRepository.findAll();
    }

    public List<CustomerGroupListResponseDto> findAllDesc() {
        return customerGroupRepository.findAllDesc().stream()
                .map(CustomerGroupListResponseDto::new)
                .collect(Collectors.toList());
    }

}