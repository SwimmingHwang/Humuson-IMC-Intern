package com.humuson.controller;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import com.humuson.domain.repository.GroupRepository;
import com.humuson.dto.customer.*;
import com.humuson.service.CustomerService;
import com.humuson.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Tag(name="그룹 관리", description = "그룹 주소록의 정보를 관리합니다.")
@RequiredArgsConstructor
@RestController
public class GroupApiController {
    private final GroupService groupService;
    private final CustomerService customerService;
    private final GroupRepository groupRepository;
    @Operation(summary="그룹 생성", description = "그룹 주소록에 그룹 정보를 추가")
    @PostMapping("/api/v1/customer/group")
    public long save(@RequestBody GroupSaveRequestDto requestDto) {
        Group group = new Group(requestDto.getGroupName(), requestDto.getGroupComment());

        List<String> customerStrList= requestDto.getCustomers();
        List<Long> idList = customerStrList.stream().map(Long::parseLong).collect(Collectors.toList());

        group.setCustomers(customerService.findAllById(idList));
        groupService.save(group);
        // TODO : return 형태 고칠 것
        return 1;
    }

    @Operation(summary="그룹 수정", description = "그룹 주소록의 그룹 정보를 수정")
    @PutMapping("/api/v1/customer/group/{id}")
    public long update(@PathVariable long id, @RequestBody GroupUpdateRequestDto requestDto) {
        List<String> customerStrList= requestDto.getCustomers();
        List<Long> idList = customerStrList.stream().map(Long::parseLong).collect(Collectors.toList());

        Set<Customer> customers = customerService.findAllById(idList);

        groupService.update(id,requestDto.getGroupName(), requestDto.getGroupComment(), customers );
        // TODO : return 형태 고칠 것
        return 1;
    }

    @Operation(summary="그룹 삭제", description = "그룹 주소록의 그룹 정보를 삭제")
    @DeleteMapping("/api/v1/customer/group/{id}")
    public long delete(@PathVariable long id) {
        groupService.delete(id);
        return id;
    }
    @Operation(summary="그룹 조회", description = "그룹 주소록의 그룹 정보를 조희")
    @GetMapping("/api/v1/customer/group/{id}")
    public Group findById(@PathVariable long id) {
        return groupService.findById(id);
    }
    
    @Operation(summary="그룹 리스트 조회", description = "그룹 주소록의 그룹 정보들을 조희")
    @GetMapping("/api/v1/customer/group/list")
    public List<Group> findAll() {
        return groupService.findAll();
    }

//    public List<GroupListResponseDto> findAll() {
//        return groupService.findAllDesc();
//    }

}