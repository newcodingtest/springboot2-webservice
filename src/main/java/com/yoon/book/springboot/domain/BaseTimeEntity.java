package com.yoon.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA ENTITY들이 BaseTimeEntity을 상속하면 필드들(createdDate,modifiedDate)도 칼럼으로 인식함
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity에 Auditing 기능을 포함시킴
public abstract class BaseTimeEntity { // 모든 엔티티들의 상위클래스가 되어 createdDate, modifiedDate을 자동으로 관리함

    @CreatedDate //Entity 가 생성되어 저장될때 시간이 자동저장
    private LocalDateTime createdDate;
    
    @LastModifiedDate // Entity 의 값이 변경될때 시간이 자동저장
    private LocalDateTime modifiedDate;
}
