/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.humuson.service;

import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.AtMsgsRepository;
import com.humuson.dto.at.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class AtMsgsService {
    private final AtMsgsRepository atMsgsRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Integer save(AtMsgsSaveRequestDto requestDto) {
        return atMsgsRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }
    @Transactional
    public List<AtMsgs> saveAll(MultiAtMsgsSaveRequestDto requestDto){
        // requestDto 를 AtMsgs 리스트로 변환
        List<AtMsgs> atMsgs = requestDto.toEntity();
        return atMsgsRepository.saveAll(atMsgs);
    }
    @Transactional
    public List<AtMsgs> saveAllList(MultiAtMsgsSaveListRequestDto requestDto){
        // requestDto 를 AtMsgs 리스트로 변환
        List<AtMsgs> atMsgs = requestDto.toEntity(customerRepository.findAll());
        return atMsgsRepository.saveAll(atMsgs);
    }

    @Transactional
    public Integer update(Integer id, AtMsgsUpdateRequestDto requestDto) {
        AtMsgs atMsgs = atMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        atMsgs.update(requestDto.getReservedDate(), requestDto.getMsg(),requestDto.getPhoneNumber(), requestDto.getTemplateCode());

        return id;
    }
    @Transactional
    public Integer updateStatus(Integer id, String status){
        AtMsgs atMsgsId = atMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        log.info("id:" + id + "status updated");
        atMsgsId.updateStatus(status);
        return id;
    }
    @Transactional
    public Integer updateStatus(Integer id, String status, boolean isReport){
        AtMsgs atMsgsId = atMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        log.info("id:" + id + "status update");
        atMsgsId.updateStatus(status, isReport);
        return id;
    }

//    @Transactional
//    public Integer updateStatusAndEtc2(Integer id, String status){
//        AtMsgs atMsgsId = atMsgsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//        atMsgsId.updateStatus(status);
//        atMsgsId.updateEtc2(atMsgsId.getEtc2() + id.toString());
//        return id;
//    }

    @Transactional
    public void delete (Integer id) {
        AtMsgs atMsgs = atMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        atMsgsRepository.delete(atMsgs);
    }

    @Transactional(readOnly = true)
    public AtMsgsResponseDto findById(Integer id) {
        AtMsgs entity = atMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new AtMsgsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<AtMsgsListResponseDto> findAllDesc() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return atMsgsRepository.findAllDesc().stream()
                .map(AtMsgsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<AtMsgs> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return atMsgsRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<AtMsgs> findAllByReservedDate(){
        return atMsgsRepository.findAllByReservedDate();
    }
}