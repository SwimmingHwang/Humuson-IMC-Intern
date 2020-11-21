/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.humuson.agent.service;

import com.humuson.agent.domain.repository.MtMsgsRepository;
import com.humuson.agent.dto.MtMsgsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class MtMsgsService {
    private final MtMsgsRepository mtMsgsRepository;

    @Transactional
    public Integer save(MtMsgsSaveRequestDto requestDto) {
        return mtMsgsRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

}