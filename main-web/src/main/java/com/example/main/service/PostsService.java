/*
 *
 * 비즈니스 로직은 service 클래스 내부에서 처리 된다. 객체는 단순히 데이터 덩어리 역할만.ㅊ
 * 서비스 베소드는 트랜잭션과 도메인 간의 순서만 보장한다.  -> 도메인 모델을 다룸.
 * */

package com.example.main.service;

//package com.jojoldu.book.springboot.service;

import com.example.main.domain.posts.Posts;
import com.example.main.domain.posts.PostsRepository;
import com.example.main.dto.PostsListResponseDto;
import com.example.main.dto.PostsResponseDto;
import com.example.main.dto.PostsSaveRequestDto;
import com.example.main.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor// final이 선언된 모든 필드를 인자값으로하는 생성자를 생성해줌.
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId(); // insert/update 쿼리 실행
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        // repo에서 넘어온 stream을 map을 통해 dto로 변환해서 리스트로 반환
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}