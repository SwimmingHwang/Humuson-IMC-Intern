package com.humuson.service;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.CustomerGroup;
import com.humuson.domain.entity.Group;
import com.humuson.domain.repository.CustomerGroupRepository;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.repository.GroupRepository;
import com.humuson.dto.customer.*;
import lombok.NonNull;
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
public class GroupService {
    @NonNull
    private final GroupRepository groupRepository;
    private final CustomerRepository customerRepository;
    private final CustomerGroupRepository customerGroupRepository;

    @Transactional
    public void save(GroupCustomerSaveRequestDto requestDto) {
        Group group = new Group(requestDto.getGroupName());
        groupRepository.save(group);

        List<String> customerIdStrList = requestDto.getCustomerIdStrList();
        for(String customerId : customerIdStrList){
            long id = Long.parseLong(customerId);
            Optional<Customer> opCustomer = customerRepository.findById(id);
            if (opCustomer.isPresent()){
                Customer customer = opCustomer.get();
                customerGroupRepository.save(new CustomerGroup(group, customer));
                log.info("그룹에 고객 추가완료! id : "+ id);
            }
            else{
                log.info("customer이 존재하지 않음!"+ id);
            }
        }
    }

    @Transactional
    public long update(long id, GroupCustomerUpdateRequestDto requestDto) {
        // id : group 인덱스, GroupCustomerURD : group 이름, 삭제할

//        List<CustomerGroup> customerGroups = customerGroupRepository.findByGroup_Id(id);
////        customerGroupRepository.updateGroupId()
////        GroupResponseDto groupResponseDto = this.findById(id);
//
//        List<String> customerIdStrList = requestDto.getNotCustomerIdStrList();
//        for(String customerId : customerIdStrList){
//            long customerLongId = Long.parseLong(customerId);
//            Optional<Customer> opCustomer = customerRepository.findById(customerLongId);
//            if (opCustomer.isPresent()){
//                Customer customer = opCustomer.get();
//                customerGroupRepository.save(new CustomerGroup(group, customer));
////                log.info("그룹에 고객 추가완료! id : "+ id);
//            }
//            else{
////                log.info("customer이 존재하지 않음!"+ id);
//            }
//        }
        return id;
    }

    @Transactional
    public void delete (long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 그륩이 없습니다. id=" + id));
        groupRepository.delete(group);
    }

    @Transactional(readOnly = true)
    public GroupResponseDto findById(long id) {
        Group entity = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new GroupResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public List<Group> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return groupRepository.findAll();
    }

    public List<GroupListResponseDto> findAllDesc() {
        return groupRepository.findAllDesc().stream()
                .map(GroupListResponseDto::new)
                .collect(Collectors.toList());
    }

}