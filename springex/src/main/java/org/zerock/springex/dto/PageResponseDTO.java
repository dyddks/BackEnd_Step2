package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/*
    jsp에서 bootstrap의 pagination컴포넌트를 사용하는데
    그 때 이 dto를 받아서 pagination을 구성한다.

    Generic을 사용한 목적은
    현재는 E에 TodoDto가 전달된다.
    하지만 다른 업무/목적으로 얼마든지 많은 테이블과 dto가 생성될 수 있다.
    이때 dtoList를 제외한 나머지 값들은 어떤 페이지에서도 페이지네이션에 필요한 데이터이다.
    dtoList만 할일 페이지 목록일때는 TodoDTO
    쇼핑몰 상품페이지 목록일때는 OrderDTO가 필요하므로
    Generic으로 만들면 범용적으로 재사용이 가능하다.
 */
@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;   // 현재 페이지
    private int size;   // 현재 보여야할 데이터 개수
    private int total;  // 테이블의 전체 데이터 개수

    private int start;  // 화면상의 시작 페이지 번호
    private int end;    // 화면상의 끝 페이지 번호

    private boolean prev;   // 이전 페이지 존재 여부 -> 존재하면 true, prev버튼이 보인다.
    private boolean next;   // 다음 페이지 존재 여부 -> 존재하면 true, next버튼이 보인다.

    private List<E> dtoList;    // 테이블에 보여줘야 할 row데이터의 list

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page/10.0)) * 10;
        this.start = this.end - 9;

        // pagination의 가장 끝 번호
        int last = (int)(Math.ceil(this.total/(double)size));
        this.end = Math.min(end, last);

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}
