package com.humuson.service;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.CustomerGroup;
import com.humuson.domain.entity.Group;
import com.humuson.domain.repository.CustomerGroupRepository;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.repository.GroupRepository;
import com.humuson.dto.customer.GroupCustomerSaveRequestDto;
import com.humuson.dto.customer.GroupListResponseDto;
import com.humuson.dto.customer.GroupResponseDto;
import com.humuson.dto.customer.GroupUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerGroupService {
    private final CustomerGroupRepository customerGroupRepository;
    private final GroupRepository groupRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public List<CustomerGroup> findByGroupId(long id){
        return customerGroupRepository.findByGroup_Id(id);
    }

//    @Transactional
//    public long update(long id, GroupUpdateRequestDto requestDto) {
//        Group group = groupRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//
//        group.updateGroupName(requestDto.getGroupName());
//
//        return id;
//    }

//
//    @Transactional
//    public void delete (long id) {
//        Group group = groupRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//
//        groupRepository.delete(group);
//    }
//
//    @Transactional(readOnly = true)
//    public GroupResponseDto findById(long id) {
//        Group entity = groupRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//
//        return new GroupResponseDto(entity);
//    }
//    @Transactional(readOnly = true)
//    public List<Group> findAll() {
//        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
//        return groupRepository.findAll();
//    }
//
//    public List<GroupListResponseDto> findAllDesc() {
//        return groupRepository.findAllDesc().stream()
//                .map(GroupListResponseDto::new)
//                .collect(Collectors.toList());
//    }

}
