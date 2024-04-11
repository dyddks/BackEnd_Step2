import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w2.todo.dao.TodoDao;
import org.zerock.w2.todo.domain.TodoVo;

import java.time.LocalDate;
import java.util.List;

public class TodoDaoTests {
    private TodoDao dao;

    @BeforeEach
    public void ready(){
        dao = new TodoDao();
    }

    @Test
    public void testTime() throws Exception{
        System.out.println(dao.getTime());
    }

    @Test
    public void testInsert() throws Exception{
        TodoVo todoVo = TodoVo.builder()
                .title("sample")
                .dueDate(LocalDate.of(2024,2,2))
                .build();

        dao.insert(todoVo);
    }

    @Test
    public void testSelectAll() throws Exception{
        List<TodoVo> todoVos = dao.selectAll();

        todoVos.forEach(e->System.out.println(e));
    }

    @Test
    public void testSelectOne() throws Exception{
        TodoVo todoVo = dao.selectOne(3L);

        System.out.println(todoVo);
    }

    @Test
    public void testDelete() throws Exception{
        dao.delete(3L);
    }

    @Test
    public void testUpdate() throws Exception{
        TodoVo todoVo = TodoVo.builder()
                .title("sample1")
                .dueDate(LocalDate.of(2024,4,2))
                .finished(true)
                .tno(5L)
                .build();
        dao.update(todoVo);
    }
}
