/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.example.main.service;


import com.example.main.domain.msgs.FtMsgs;
import com.example.main.domain.msgs.FtMsgsRepository;
import com.example.main.dto.FtMsgsListResponseDto;
import com.example.main.dto.FtMsgsResponseDto;
import com.example.main.dto.FtMsgsSaveRequestDto;
import com.example.main.dto.FtMsgsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class FtMsgsService {
    private final FtMsgsRepository ftMsgsRepository;

    @Transactional
    public Integer save(FtMsgsSaveRequestDto requestDto) {
        return ftMsgsRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

    @Transactional
    public Integer update(Integer id, FtMsgsUpdateRequestDto requestDto) {
        FtMsgs ftMsgs = ftMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        ftMsgs.update(requestDto.getMsg());

        return id;
    }

    @Transactional
    public void delete (Integer id) {
        FtMsgs ftMsgs = ftMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        ftMsgsRepository.delete(ftMsgs);
    }

    @Transactional(readOnly = true)
    public FtMsgsResponseDto findById(Integer id) {
        FtMsgs entity = ftMsgsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new FtMsgsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<FtMsgsListResponseDto> findAllDesc() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return ftMsgsRepository.findAllDesc().stream()
                .map(FtMsgsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<FtMsgs> findAll() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return ftMsgsRepository.findAll();
    }
}