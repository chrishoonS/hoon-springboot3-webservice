package com.chrishoon.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON으로 반환하는 컨트롤러로 생성
// @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용해주는 어노테이션
@RestController
public class HelloController {

    // Get 요청을 받을 수 있는 API
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
