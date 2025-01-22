package com.chrishoon.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Posts Class로 Database를 접근하게 해줄 repository
    // ibatis나 Mybatis 등에서 DAO라 불리는 DB Layer 접근자
    // JpaRepository<Entity 클래스, Pk 타입> 상속 시 기본 CRUD 메서드 자동으로 생성
    // Repository는 Entity 클래스와 같은 경로에 위치!

    @Query("SELECT p FROM Posts AS p ORDER BY p.id DESC")   //정렬된 데이터를 반환하도록 구현
    List<Posts> findAllDesc();
}

// Querydsl 추천
// 타입 안정성 보장 : 메소드를 기반으로 쿼리 생성
// 국내 많은 회사에서 사용중으로 레퍼런스가 많음
//