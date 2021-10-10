package com.yoon.book.springboot.dto.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자 자동추가
@Entity//JPA의 어노테이션 테이블과 링크될 클래스임을 나타낸다.
public class Posts {

    @Id// 해당 테이블의 PK필드를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK의 생성규칙-GenerationType.IDENTITY 을 추가해야만 Auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false)//테이블의 컬럼을 나타난다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder// 해당 위치의 생성자에 포함된 필드만 빌더에 포함-->빌더를 사용하게되면 데이터 기입시 보다 명확하게 어떤 데이터가 어떤 위
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
