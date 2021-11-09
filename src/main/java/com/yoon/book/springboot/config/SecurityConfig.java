package com.yoon.book.springboot.config;

import com.yoon.book.springboot.config.auth.CustomOAuth2UserService;
import com.yoon.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //스프링 시큐리티를 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사용하기위해 해당 옵션 disable 해준다
                .and()
                .authorizeRequests() //url 별 권한관리를 설정하는 시작점, antMatchers을 쓰려면 authorizeRequests을 사용해야함
                .antMatchers("/", "/css/**", "/images/**", "/js/**" , "/h2-console/**").permitAll()// antMatchers: 권한관리대상 지정,url, http 메소드별 관리가 가능,
                //'/' 등 지정된 url들은 permitAll() 옵션을 통해 전체 열람권한줌
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // '/api/v1/**' 주소를 가진 api는 user권한을 가진 사람만 열람가능
                .anyRequest().authenticated() //anyRequest(): 설정된 값들 이외에 나머지 값들, authenticated()을 추가하여 나머지 url은 인증된 사용자만 접근
                .and().logout().logoutSuccessUrl("/") //로그아웃의 시작점, 로그아웃시 해당 url로 이동
                .and()
                .oauth2Login() //oauth2 로그인 기능의 시작점
                .userInfoEndpoint() //oauth2 로그인 이후 사용자 정보를 가져올때의 설정
                .userService(customOAuth2UserService); //소셜 로그인 후의 후속조치를 진행할 userService 인터페이스의 구현체 등록

    }
}