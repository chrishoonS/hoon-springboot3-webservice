package com.chrishoon.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

// Junit4 이용하는 경우
@RunWith(SpringRunner.class)        // SpringRunner 스프링 실행자 사용
@WebMvcTest(HelloController.class)  // 컨트롤러 관련 어노테이션 사용가능
class HelloControllerTest {

    @Autowired              // 스프링이 관리하는 빈 주입받음
    private MockMvc mvc;    // 웹 API 테스트 시 사용, 스프링 MVC 시작점

    // Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation:
    // 프로젝트의 JDK 버전과 Mockito 설정 간의 호환성에 대한 사전 알림

    // 해결책
    // mockito-inline 의존성을 추가
    // JDK 모듈 접근을 허용하는 JVM 옵션을 사용하는 것도 가능
    // JVM 옵션 : --add-opens java.base/java.lang=ALL-UNNAMED
    // 최신 Mockito 버전을 사용하여 최신 변경사항을 적용

    @Test
    public void hello_리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))           // get 요청, 여러 체이닝 지원
                .andExpect(status().isOk())             // Http header의 상태 검증
                .andExpect(content().string(hello));    // 응답 본문의 내용 검증

    }

    @Test
    public void helloDto_리턴() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)                        // 요청 파라미터 설정, only String
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))        // JSON 응답값을 필드별로 검증, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}

//Junit5 이용하는 경우
/*@WebFluxTest(HelloController.class) // WebFlux와 관련된 테스트를 설정
class HelloControllerTest {

    @Autowired
    private WebTestClient webTestClient; // WebTestClient 사용

    @Test
    @DisplayName("hello 리턴 테스트")
    public void returnHello() {
        String hello = "hello";

        webTestClient.get()                                 // HTTP GET 요청
                .uri("/hello")                          // URI 지정
                .exchange()                                 // 요청 실행
                .expectStatus().isOk()                      // HTTP 상태 코드 검증
                .expectBody(String.class).isEqualTo(hello); // 응답 본문 내용 검증
    }

    @Test
    @DisplayName("helloDto 리턴 테스트")
    public void returnHelloDto() {
        String name = "hello";
        int amount = 1000;

        webTestClient.get().uri(uriBuilder -> uriBuilder.path("/hello/dto")
                        .queryParam("name", name)
                        .queryParam("amount", amount)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()                                         // 응답 본문 검증
                .jsonPath("$.name").isEqualTo(name)         // JSON 경로 "name" 필드 확인
                .jsonPath("$.amount").isEqualTo(amount);    // JSON 경로 "amount" 필드 확인

    }
}*/
