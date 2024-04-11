package org.zerock.w2.todo.service;

import org.modelmapper.ModelMapper;
import org.zerock.w2.todo.dao.TodoDao;
import org.zerock.w2.todo.domain.TodoVo;
import org.zerock.w2.todo.dto.TodoDto;
import org.zerock.w2.todo.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

public enum TodoService {
    INSTANCE;

    private TodoDao todoDao;
    private ModelMapper modelMapper;

    TodoService() {
        todoDao = new TodoDao();
        modelMapper = MapperUtil.INSTANCE.getModelMapper();
    }

    // List<Vo>를 List<Dto>로 맵핑하려면
    // Vo요소를 하나씩 꺼내서 Dto로 modelMapper를 이용해 변환 시켜준 뒤
    // 다시 리스트로 만들어주어야 한다.
    public List<TodoDto> listAll() throws Exception{
        List<TodoVo> voList = todoDao.selectAll();
        List<TodoDto> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDto.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public void register(TodoDto todoDto) throws Exception{
        TodoVo todoVo = modelMapper.map(todoDto, TodoVo.class);

        System.out.println("todoVo = " + todoVo);

        todoDao.insert(todoVo);
    }

    public TodoDto listOne(Long tno) throws Exception{
        TodoVo todoVo = todoDao.selectOne(tno);
        TodoDto todoDto = modelMapper.map(todoVo, TodoDto.class);

        return todoDto;
    }

    public void update(TodoDto todoDto) throws Exception{
        TodoVo todoVo = modelMapper.map(todoDto, TodoVo.class);

        todoDao.update(todoVo);
    }

    public void delete(Long tno) throws Exception {
        todoDao.delete(tno);
    }
}
