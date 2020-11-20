/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.humuson.service;

import com.humuson.domain.msgs.Msgs;
import com.humuson.domain.msgs.MsgsRepository;
import com.humuson.dto.msg.MsgsListResponseDto;
import com.humuson.dto.msg.MsgsResponseDto;
import com.humuson.dto.msg.MsgsSaveRequestDto;
import com.humuson.dto.msg.MsgsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class MsgsService {
    private final MsgsRepository msgsRepository;

    @Transactional
    public Integer save(MsgsSaveRequestDto requestDto) {
        return msgsRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

    @Transactional
    public Integer update(Integer id, MsgsUpdateRequestDto requestDto) {
        Msgs msgs = msgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        msgs.update(requestDto.getMsg());

        return id;
    }

    @Transactional
    public void delete (Integer id) {
        Msgs msgs = msgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        msgsRepository.delete(msgs);
    }

    @Transactional(readOnly = true)
    public MsgsResponseDto findById(Integer id) {
        Msgs entity = msgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new MsgsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MsgsListResponseDto> findAllDesc() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return msgsRepository.findAllDesc().stream()
                .map(MsgsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<Msgs> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return msgsRepository.findAll();
    }
}