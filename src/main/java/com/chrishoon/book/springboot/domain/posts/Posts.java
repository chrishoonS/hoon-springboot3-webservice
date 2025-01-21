package com.chrishoon.book.springboot.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 자동추가
// 코틀린에서는 위 롬복 어노테이션 불필요
@Entity              // 테이블과 링크될 클래스, 언더스코어 네이밍으로 테이블 이름 매칭
public class Posts { //실제 DB 테이블과 매칭될 클래스 = Entity Class
    // Entity 클래스에서는 절대 Setter 메서드를 만들지 않음!!!!
    // 생성자를 통해 최종값 채운 후 DB insert
    // 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메서드 호출

    @Id                                                 // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, 옵션 추가로 auto_increment
    private Long id;

    @Column(length = 500, nullable = false)             // 테이블 컬럼, 문자열 기본값 varchar(255)으로 옵션 설정을 통해 변경 가능
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스 생성, 생성자에 포함된 필드만 빌더에 포함
    // 어느 필드에 어떤 값을 채울지 명확하게 인지 가능
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
