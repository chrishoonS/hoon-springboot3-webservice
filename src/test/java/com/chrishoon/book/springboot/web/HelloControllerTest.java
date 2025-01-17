package com.chrishoon.book.springboot.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Junit4 이용하는 경우
/*@RunWith(SpringRunner.class)        // SpringRunner 스프링 실행자 사용
@WebMvcTest(HelloController.class)  // 컨트롤러 관련 어노테이션 사용가능
class HelloControllerTest {

    @Autowired              // 스프링이 관리하는 빈 주입받음
    private MockMvc mvc;    // 웹 API 테스트 시 사용, 스프링 MVC 시작점

    // Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation:
    // 프로젝트의 JDK 버전과 Mockito 설정 간의 호환성에 대한 사전 알림
    *//*
        해결책
        - mockito-inline 의존성을 추가
        - JDK 모듈 접근을 허용하는 JVM 옵션을 사용하는 것도 가능
          JVM 옵션 : --add-opens java.base/java.lang=ALL-UNNAMED
        - 최신 Mockito 버전을 사용하여 최신 변경사항을 적용
     *//*
    @Test
    public void hello_리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))           // get 요청, 여러 체이닝 지원
                .andExpect(status().isOk())             // Http header의 상태 검증
                .andExpect(content().string(hello));    // 응답 본문의 내용 검증

    }
}*/

//Junit5 이용하는 경우
@WebFluxTest(HelloController.class) // WebFlux와 관련된 테스트를 설정
class HelloControllerTest {

    @Autowired
    private WebTestClient webTestClient; // WebTestClient 사용

    @Test
    @DisplayName("hello 리턴 테스트")
    public void returnHello() throws Exception {
        String hello = "hello";

        webTestClient.get() // HTTP GET 요청
                .uri("/hello") // URI 지정
                .exchange() // 요청 실행
                .expectStatus().isOk() // HTTP 상태 코드 검증
                .expectBody(String.class).isEqualTo(hello); // 응답 본문 내용 검증
    }
}
