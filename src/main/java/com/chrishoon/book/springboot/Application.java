package com.chrishoon.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 부트 자동설정, 스프링 빈 읽기&생성 모두 자동 설정
// 항상 프로젝트 최상단 위치!!
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 웹 애플리케이션 서버 실행(내부에서 WAS 실행하는 것 포함)
        // 이 말인 즉슨 톱캣 설치 필요 없이 스프링 부트로 만들어진 Jar 파일로 실행
        SpringApplication.run(Application.class, args);
    }
}
