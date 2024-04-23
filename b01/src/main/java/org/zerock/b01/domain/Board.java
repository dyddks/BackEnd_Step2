package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;

// 이클래스의 정보를 가지고 자동으로 Board Table 생성
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity{
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql/MariaDB에서 AutoIncreament속성
    private Long bno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
