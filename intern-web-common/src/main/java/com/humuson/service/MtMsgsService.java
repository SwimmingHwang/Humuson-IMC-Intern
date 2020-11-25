/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.humuson.service;

import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.repository.CustomerRepository;
import com.humuson.domain.msgs.MtMsgs;
import com.humuson.domain.msgs.MtMsgsRepository;
import com.humuson.dto.mt.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class MtMsgsService {
    private final MtMsgsRepository mtMsgsRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Integer save(MtMsgsSaveRequestDto requestDto) {
        return mtMsgsRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

    @Transactional
    public List<MtMsgs> saveAll(MultiMtMsgsSaveRequestDto requestDto){
        // requestDto 를 AtMsgs 리스트로 변환
        List<MtMsgs> mtMsgs = requestDto.toEntity();
        return mtMsgsRepository.saveAll(mtMsgs);
    }
    @Transactional
    public List<MtMsgs> saveAllList(MultiMtMsgsSaveListRequestDto requestDto){
        // requestDto 를 AtMsgs 리스트로 변환
        List<MtMsgs> mtMsgs = requestDto.toEntity(customerRepository.findAll());
        return mtMsgsRepository.saveAll(mtMsgs);
    }


    @Transactional
    public Integer update(Integer id, MtMsgsUpdateRequestDto requestDto) {
        MtMsgs msgs = mtMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        msgs.update(requestDto.getReservedDate(), requestDto.getMtType(), requestDto.getCallback(),
                requestDto.getMsg(), requestDto.getPhoneNumber());

        return id;
    }
    @Transactional
    public Integer updateStatus(Integer id, String status){
        MtMsgs mtMsgsId = mtMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        log.info("id:" + id + "status updated");
        mtMsgsId.updateStatus(status);
        return id;
    }
    @Transactional
    public void delete (Integer id) {
        MtMsgs msgs = mtMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        mtMsgsRepository.delete(msgs);
    }

    @Transactional(readOnly = true)
    public MtMsgsResponseDto findById(Integer id) {
        MtMsgs entity = mtMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new MtMsgsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MtMsgsListResponseDto> findAllDesc() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return mtMsgsRepository.findAllDesc().stream()
                .map(MtMsgsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<MtMsgs> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return mtMsgsRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<MtMsgs> findAllByReservedDate(){
        return mtMsgsRepository.findAllByReservedDate();
    }


}