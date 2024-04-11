package org.zerock.w2.todo.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data   // getter/setter/toString/equals/hashcode 모두 오버라이딩
@AllArgsConstructor // 매개변수 없는 생성자
@NoArgsConstructor  // 모든 필드를 초기화 할 수 있는 생성자        ModelMapper로 Dto <-> Vo 변환
public class TodoDto {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
