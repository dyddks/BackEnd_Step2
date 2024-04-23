package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.search.BoardSearch;

/*
    인터페이스를 만들고 JpaRepository<Board, Long>를 상속받는데
    제너릭에 <사용할 테이블 클래스명, 기본기의 자료형>를 입력하면
    자동으로 Jpa라이브러리에 의해서
    자식 객체가 생성되어 Spring Container에 포함된다.
    
    Board에 대한 입출력이 필요하면
    @Autowired
    private BoardRepository boardRepository;
    혹은 생성자 주입을 통해서 사용하면 된다.
 */
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    @Query("select b from Board b where b.title like concat('%', :keyword, '%')")
    Page<Board> findKeyword(String keyword, Pageable pageable);
}
