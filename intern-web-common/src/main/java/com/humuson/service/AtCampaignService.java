

package com.humuson.service;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import com.humuson.domain.repository.AtCampaignRepository;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.repository.GroupRepository;
import com.humuson.dto.customer.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AtCampaignService {

    private final AtCampaignRepository atCampaignRepository;

    @Transactional
    public AtCampaign save(AtCampaign atCampaign){
        return atCampaignRepository.save(atCampaign);
    }

//    @Transactional
//    public Long update(long id, String groupName, String groupComment, Set<Customer> customerSet) {
//        Group group = groupRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 그룹 정보가 없습니다. id=" + id));
//
//        // 삭제 로직 여기서 처리 그 외 업데이트는 엔터티 자체에서
//        Set<Customer> setCha = new HashSet<>(group.getCustomers()); //차집합 (삭제해줘야할 customers)
//        setCha.removeAll(customerSet);
//
//        List<Long> idxList = new ArrayList<>();
//        for (Customer customer : setCha) {
//            idxList.add(customer.getId());
//        }
//        if (!idxList.isEmpty())
//            groupRepository.deleteCustomers(id,idxList);
//
//        group.update(groupName, groupComment, customerSet.size(), customerSet);
//        return id;
//    }
//
//    @Transactional
//    public void delete (long id) {
//        Group group = groupRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 그륩이 없습니다. id=" + id));
//        group.setCustomerCount(group.getCustomers().size());
//        groupRepository.delete(group);
//    }
//
//    @Transactional(readOnly = true)
//    public Group findById(long id) {
//        Group entity = groupRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//
//        return entity;
//    }
//    @Transactional(readOnly = true)
//    public List<Group> findAll() {
//        return groupRepository.findAll();
//    }
//
//    public List<GroupListResponseDto> findAllDesc() {
//        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
//        return groupRepository.findAllDesc().stream()
//                .map(GroupListResponseDto::new)
//                .collect(Collectors.toList());
//    }
//
//    public List<Group> saveAll(List<Group> groups) {
//        return groupRepository.saveAll(groups);
//    }
//
//    public List<Long> findAllCustomersIds(long id) {
//        return groupRepository.findAllCustomersIds(id);
////                .stream()
////                .map(String::valueOf)
////                .collect(Collectors.toList());
//    }
}