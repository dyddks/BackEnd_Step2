import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.w2.todo.dto.TodoDto;
import org.zerock.w2.todo.service.TodoService;

import java.time.LocalDate;

@Log4j2
public class TodoServiceTests {

    @Test
    public void registServiceTest() throws Exception{
        TodoDto dto = new TodoDto().builder()
                .tno(7L)
                .title("sample todo")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        TodoService.INSTANCE.register(dto);
    }

    @Test
    public void listAllTest() throws Exception{
        TodoService.INSTANCE.listAll();
    }

    @Test
    public void listOneTest() throws Exception{
        TodoDto todoDto = TodoService.INSTANCE.listOne(4L);

        log.info(todoDto);
    }

    @Test
    public void deleteTest() throws Exception{
        TodoService.INSTANCE.delete(4L);
    }

    @Test
    public void updateTest() throws Exception{
        TodoDto dto = new TodoDto().builder()
                .tno(7L)
                .title("sample todo...gg")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        log.info(dto);
        TodoService.INSTANCE.update(dto);
    }
}
