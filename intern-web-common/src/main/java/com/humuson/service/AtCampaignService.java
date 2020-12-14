

package com.humuson.service;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.repository.AtCampaignRepository;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.repository.GroupRepository;
import com.humuson.dto.at.AtCampaignSaveRequestDto;
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

    @Transactional(readOnly = true)
    public List<AtCampaign> findAllReservedDateDesc() {
        return atCampaignRepository.findAllReservedDateDesc();
    }
    @Transactional
    public Long update(long id, AtCampaignSaveRequestDto atCampaignSaveRequestDto) {
        AtCampaign atCampaign = atCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림톡 캠페인 정보가 없습니다. id=" + id));

        atCampaign.update(atCampaignSaveRequestDto.getCampName(), atCampaignSaveRequestDto.getReservedDate(),
                atCampaignSaveRequestDto.getSenderKey(),atCampaignSaveRequestDto.getCount(), atCampaignSaveRequestDto.getMsg(),
                atCampaignSaveRequestDto.getTemplateInfoC());

        return id;
    }

    @Transactional
    public void delete(long id) {
        AtCampaign atCampaign = atCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림톡 캠페인 정보가 없습니다. id" + id));
        atCampaignRepository.delete(atCampaign);
    }

    @Transactional
    public long updateStatus(long id, String status){
        AtCampaign atCampaign = atCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림톡 캠페인 정보가 없습니다. id" + id));
        log.info("id:" + id + "status updated");
        atCampaign.updateStatus(status);
        return id;
    }

    public List<AtCampaign> findAllByReservedDate() {
        return atCampaignRepository.findAllByReservedDate();
    }

    public AtCampaign findById(long id) {
        AtCampaign atCampaign = atCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림톡 캠페인 정보가 없습니다. id" + id));
        return atCampaign;
    }
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