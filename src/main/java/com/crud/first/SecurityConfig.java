package com.crud.first;

import com.crud.first.board.Board;
import com.crud.first.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final BoardRepository boardRepository;

  public Board getFirstBoardNum() {
    return boardRepository.findAll().getFirst();
  }

  //기존
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.csrf((csrf) -> csrf.disable());
//
//    http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll());
//
//    http.formLogin((formLogin) -> formLogin.loginPage("/login").defaultSuccessUrl("/board/"+getFirstBoardNum().getBoardId()));
//
//    http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));
//
//    return http.build();
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // CSRF 보호 비활성화
    http.csrf((csrf) -> csrf.disable());
    // 요청 권한 설정
    http.authorizeHttpRequests((authorize) -> {
      authorize.requestMatchers("/","home.css","/dog.png").permitAll(); // "/" 경로는 모든 사용자에게 허용
      authorize.anyRequest().authenticated(); // 그 외 모든 요청은 인증된 사용자만 접근 가능
    });

    // 로그인 설정
    http.formLogin((formLogin) ->
            formLogin.loginPage("/login")
                    .defaultSuccessUrl("/board/"+getFirstBoardNum().getBoardId())
                    .permitAll()); // 로그인 페이지는 모든 사용자가 접근 가능해야 함

    // 로그아웃 설정
    http.logout((logout) ->
            logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/")); // 로그아웃 성공 시 "/" 로 리다이렉션

    // 세션 관리 설정 (세션 만료 시 "/"로 리다이렉션)
    http.sessionManagement((session) ->
            session.invalidSessionUrl("/")); // 세션이 유효하지 않을 때 리다이렉트할 URL 설정

    return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
