package com.yoon.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 어노테이션 활성화
@SpringBootApplication
// @SpringBootApplication이 있는 위치부터 스프링이 설정일 읽어가기 때문에 항상 프로젝트의 최상단에 위치해야한다.
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args); //내장 WAS를 실행시키는 것
    }
}
