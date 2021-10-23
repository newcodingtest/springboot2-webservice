package com.yoon.book.springboot.service.posts;

import com.yoon.book.springboot.domain.posts.Posts;
import com.yoon.book.springboot.web.dto.PostsListResponseDto;
import com.yoon.book.springboot.web.dto.PostsResponseDto;
import com.yoon.book.springboot.web.dto.PostsSaveRequestDto;
import com.yoon.book.springboot.domain.posts.PostsRepository;
import com.yoon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor //final 이 선언된 모든 필드를 인자값으로 생성자를 생성
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readOnly = true : 트랜잭션 범위는 유지하면서 조회 기능만 남겨두어 조회 속도가 개선되어, 등록/수정/삭제 기능이 없는 메소드에 사용하는 것을 추천되어진다.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        //Jpa에서 Repository를 지원하고있음
        postsRepository.delete(posts);
    }
}
