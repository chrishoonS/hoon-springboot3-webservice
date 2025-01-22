package com.chrishoon.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/* spring security 6.xx 버전 미만
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
*/

// spring security 6.xx 버전 이상
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // H2 Console을 사용하거나 API 요청이 주로 JSON 데이터를 주고받을 경우 불필요한 CSRF 토큰 검사를 방지
                .csrf(AbstractHttpConfigurer::disable)
                // frameOptions를 비활성화하여 브라우저의 프레임 관련 보안 정책을 완화
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // H2 Console 사용을 위한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 지정된 경로는 인증 없이 누구나 접근 가능하도록 허용
                        .requestMatchers("/api/v1/**").hasRole("USER")                                                 // /api/v1/** 경로는 ROLE_USER 권한을 가진 사용자만 접근 가능
                        .anyRequest().authenticated()                                                                   // 그 외 모든 요청 인증 필요
                )
                .logout(logout -> logout.logoutSuccessUrl("/"))  // 로그아웃 성공 시 리다이렉트 URL
                .oauth2Login(oauth2 -> oauth2               // 소셜 로그인 및 사용자 인증을 처리, customOAuth2UserService를 설정하여 사용자 정보를 가져오고 인증 처리에 사용
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // OAuth2 사용자 서비스 설정
                        )
                );

        // 모든 보안 설정이 완료된 HttpSecurity 객체를 SecurityFilterChain으로 빌드하여 반환
        return http.build();
    }
}