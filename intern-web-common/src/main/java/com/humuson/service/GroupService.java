package com.humuson.service;

import com.humuson.domain.entity.Customer;
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
import java.util.Optional;
import java.util.Set;
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
        group.setCustomerCount(group.getCustomers().size());
        return groupRepository.save(group);
    }

    @Transactional
    public long update(long id, String groupName, String groupComment, Set<Customer> customerSet) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 그룹 정보가 없습니다. id=" + id));

        group.update(groupName, groupComment, customerSet);
        return id;
    }

    @Transactional
    public void delete (long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 그륩이 없습니다. id=" + id));
        group.setCustomerCount(group.getCustomers().size());
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