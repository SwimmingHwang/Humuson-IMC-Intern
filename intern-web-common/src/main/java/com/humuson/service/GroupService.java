package com.humuson.service;

import com.humuson.domain.entity.Group;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.repository.GroupRepository;
import com.humuson.dto.customer.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {
    @NonNull
    private final GroupRepository groupRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Group save(Group group){
        log.info("Group info : " + group.toString());
        return groupRepository.save(group);
    }
//    @Transactional
//    public void save(GroupCustomerSaveRequestDto requestDto) {
//        Group group = new Group(requestDto.getGroupName());
//
//        List<String> customerIdStrList = requestDto.getCustomerIdStrList();
//        for(String customerId : customerIdStrList){
//            long id = Long.parseLong(customerId);
//            Optional<Customer> opCustomer = customerRepository.findById(id);
//            if (opCustomer.isPresent()){
//                Customer customer = opCustomer.get();
//                group.getCustomers().add(customer);
//                log.info("그룹에 고객 추가완료! id : "+ id);
//            }
//            else{
//                log.info("customer이 존재하지 않음!"+ id);
//            }
//        }
//        groupRepository.save(group);
//    }

    @Transactional
    public long update(long id, GroupCustomerUpdateRequestDto requestDto) { return id;
    }

    @Transactional
    public void delete (long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 그륩이 없습니다. id=" + id));
        groupRepository.delete(group);
    }

    @Transactional(readOnly = true)
    public Group findById(long id) {
        Group entity = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return entity;
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

    public List<Group> saveAll(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }
}