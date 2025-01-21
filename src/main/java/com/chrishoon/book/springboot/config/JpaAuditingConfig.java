package com.chrishoon.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
    // @EnableJpaAuditing : JPA Auditing 활성화
    // @SpringBootApplication과 같이 쓰면 @EnableJpaAuditing 애노테이션 기능이 활성화 되면서 Jpa 관련 빈들을 사용하려고 하는데, Test시에 Jpa 관련 빈들을 등록 하지 않았기 때문에 에러.
    // 때문에 이처럼 따로 분리한 상태
    // 출처 : https://januaryman.tistory.com/519
}
