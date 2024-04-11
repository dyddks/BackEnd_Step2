package org.zerock.w2.todo.domain;

import lombok.*;

import java.time.LocalDate;

/*
    Vo는 처음 객체가 생성될 때 생성자를 통해서 1번 초기화하면
    더 이상 값을 변경하지 못하도록 만드는 의도

    Vo는 다시 Dto로 변환시켜서 Service-Controller-View 계층으로 전달된다.
 */
@Getter
@Builder    // 빌더 패턴, 생성자 초기화지만 마치 setter처럼 초기화
@ToString
@AllArgsConstructor // 매개변수 없는 생성자
@NoArgsConstructor  // 모든 필드를 초기화 할 수 있는 생성자
public class TodoVo {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
