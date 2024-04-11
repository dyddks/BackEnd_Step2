package org.zerock.w2.todo.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVo {
    private String mid;
    private String mpw;
    private String mname;
}
