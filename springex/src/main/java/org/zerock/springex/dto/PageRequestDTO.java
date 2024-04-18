package org.zerock.springex.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;
    private String link;

    public int getSkip() {
        return (page - 1) * 10;
    }

    /*
        /todo/list에서 /todo/read, /todo/modify등으로 이동했다가
        다시 /todo/list로 돌아올 때 현재 내 페이지로 돌아오도록 하기 위해
        이 링크정보를붙여서 url이동을 하는 용도로 사용한다.
     */
    public String getLink(){
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);

        if(finished){
            builder.append("&finished=on");
        }

        if(types != null && types.length > 0){
            for(int i=0; i<types.length; i++){
                builder.append("&type=" + types[i]);
            }
        }

        if(keyword != null){
            try{
                builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }

        if(from != null){
            builder.append("&from=" + from.toString());
        }

        if(to != null){
            builder.append("&to=" + to.toString());
        }

        return builder.toString();
    }

    //--------------------------------------------------------------------------------------------------------

    private String[] types;     // 제목(t), 작성자(w)
    private  String keyword;    // 검색어
    private boolean finished;   // 완료여부
    private LocalDate from;     // 시작시간
    private LocalDate to;       // 종료시간

    public boolean checkType(String type){
        if(types == null || types.length == 0){
            return false;
        }
        // types내의 type과 1개라도 일치하면 true를 리턴
        // 1개도 일치하지 않으면 false를 리턴
        return Arrays.stream(types).anyMatch(type::equals);
    }
}
