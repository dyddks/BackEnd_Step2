package org.zerock.w2.todo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String mid;
    private String mpw;
    private String mname;
}
