package com.chrishoon.book.springboot.service;

import com.chrishoon.book.springboot.domain.posts.Posts;
import com.chrishoon.book.springboot.domain.posts.PostsRepository;
import com.chrishoon.book.springboot.web.dto.PostsListResponseDto;
import com.chrishoon.book.springboot.web.dto.PostsResponseDto;
import com.chrishoon.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        // update에서는 JPA의 영속성 컨텍스트 때문에 데이터베이스 쿼리를 날리는 부분이 없음
        // 영속성 컨텍스트 : 엔티티를 영구 저장하는 환경(논리적 개념)
        // JPA의 핵심은 엔티티가 영속성 컨텍스트에 포함 여부!!
        // Transaction 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
        // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분 반영
        // 즉 Entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다(더티체킹)
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        // 데이터가 존재하면 Optional 안에 값이 있고, 그렇지 않으면 Optional.empty를 반환


        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 조회 기능만 남겨 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc(){
        // 반환된 List<Posts> 데이터를 순차적으로 처리/변환 할 수 있는 스트림(Stream) 형태로 변환
        // Posts 엔티티를 PostsListResponseDto 객체로 변환
        // stream 데이터를 다시 list 데이터로 변환
        // 성능 고려: 만약 데이터가 매우 크다면 Stream 대신 ParallelStream을 사용하는 것이 성능상 이점이 있을 수 있음
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts); // JpaRepository에서 지원하는 delete 메서드 이용
    }
}
