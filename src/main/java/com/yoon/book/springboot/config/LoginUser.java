package com.yoon.book.springboot.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 해당 어노테이션이 생성되는 위치를 지정, PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체만 사용할수있음
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //@interface: LoginUser 라는 이름을 가진 어노테이션이 생성됨
}
