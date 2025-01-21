package com.chrishoon.book.springboot.web.dto;

import com.chrishoon.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    // Entity 클래스와 유사하지만 Entity 클래스를 Request/Response 클래스로 사용하면 X
    // Request/Response Dto는 view를 위한 클래스라 자주 변경이 필요
    // 반드시 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
