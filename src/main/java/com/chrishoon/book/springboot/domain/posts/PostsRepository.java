package com.chrishoon.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Posts Class로 Database를 접근하게 해줄 repository
    // ibatis나 Mybatis 등에서 DAO라 불리는 DB Layer 접근자
    // JpaRepository<Entity 클래스, Pk 타입> 상속 시 기본 CRUD 메서드 자동으로 생성
    // Repository는 Entity 클래스와 같은 경로에 위치!
}
