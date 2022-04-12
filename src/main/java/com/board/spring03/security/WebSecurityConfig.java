package com.board.spring03.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    } //패스워드 암호화

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();


        http.authorizeRequests()
//                .antMatchers("/user/login").permitAll()
//                .antMatchers("/user/signup").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/board/replies/**").permitAll()
//                .antMatchers("/board/view/**").permitAll()

                .antMatchers("/board/write").authenticated()
                .anyRequest().permitAll()
                //조건 추가. 그리고~~~
                .and()
                    // 로그인 기능 허용
                    .formLogin()
                    //로그인하게 될 페이지 경로 지정.(GET요청)
                    .loginPage("/user/login")
                    //로그인 처리 (POST요청)
                    .loginProcessingUrl("/user/login")
                    //성공하게 되면 이동할 경로지정.
                    .defaultSuccessUrl("/")
                    //실패했을 때
                    .failureUrl("/user/login?error")
                //조건 추가. 그리고~~~
                .and()
                    // 로그아웃 기능 허용
                    .logout()
                    //로그아웃 URL (post 요청)
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/user/login");


    }
}