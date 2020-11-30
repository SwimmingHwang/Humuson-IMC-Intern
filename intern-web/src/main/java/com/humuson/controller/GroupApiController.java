package com.humuson.controller;

import com.humuson.dto.customer.*;
import com.humuson.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name="그룹 관리", description = "그룹 주소록의 정보를 관리합니다.")
@RequiredArgsConstructor
@RestController
public class GroupApiController {
    private final GroupService groupService;


    @Operation(summary="그룹 생성", description = "그룹 주소록에 그룹 정보를 추가")
    @PostMapping("/api/v1/customer/group")
    public long save(@RequestBody GroupCustomerSaveRequestDto requestDto) {
        log.info(requestDto.toString());
        groupService.save(requestDto);
        return 1;
    }
//    @Operation(summary="그룹 생성", description = "그룹 주소록에 그룹 정보를 추가")
//    @PostMapping("/api/v1/customer/group")
//    public long save(@RequestBody GroupSaveRequestDto requestDto) {
//        return groupService.save(requestDto);
//    }

    @Operation(summary="그룹 수정", description = "그룹 주소록의 그룹 정보를 수정")
    @PutMapping("/api/v1/customer/group/{id}")
    public long update(@PathVariable long id, @RequestBody GroupUpdateRequestDto requestDto) {
        return groupService.update(id, requestDto);
    }
    @Operation(summary="그룹 삭제", description = "그룹 주소록의 그룹 정보를 삭제")
    @DeleteMapping("/api/v1/customer/group/{id}")
    public long delete(@PathVariable long id) {
        groupService.delete(id);
        return id;
    }
    @Operation(summary="그룹 조회", description = "그룹 주소록의 그룹 정보를 조희")
    @GetMapping("/api/v1/customer/group/{id}")
    public GroupResponseDto findById(@PathVariable long id) {
        return groupService.findById(id);
    }
    
    @Operation(summary="그룹 리스트 조회", description = "그룹 주소록의 그룹 정보들을 조희")
    @GetMapping("/api/v1/customer/group/list")
    public List<GroupListResponseDto> findAll() {
        return groupService.findAllDesc();
    }

    // customer의 그룹 정보 업데이트


}