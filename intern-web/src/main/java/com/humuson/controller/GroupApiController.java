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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        /* String index List로 받았을 시 Set으로 변환
         * html form에서 객체 post시 사용
         * */
        Group group = new Group(requestDto.getGroupName());

        List<String> customerStrList= requestDto.getCustomers();

        for(String customerStr : customerStrList){
            long id = Long.parseLong(customerStr);
            Customer customer= customerService.findById(id);
            group.getCustomers().add(customer);
        }
        groupService.save(group);
        // TODO : return 형태 고칠 것
        return 1;
    }

    @Operation(summary="그룹 수정", description = "그룹 주소록의 그룹 정보를 수정")
    @PutMapping("/api/v1/customer/group/{id}")
    public long update(@PathVariable long id, @RequestBody GroupUpdateRequestDto requestDto) {
        List<String> customerStrList= requestDto.getCustomers();
        Set<Customer> customers = new HashSet<>();

        for(String customerStr : customerStrList){
            long customerId = Long.parseLong(customerStr);
            Customer customer= customerService.findById(customerId);
            customers.add(customer);
        }
        return groupService.update(id,requestDto.getGroupName(), customers );
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
    public List<GroupListResponseDto> findAll() {
        return groupService.findAllDesc();
    }

    // customer의 그룹 정보 업데이트


}