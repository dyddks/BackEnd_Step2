package org.zerock.w1.todo.controller;

import lombok.SneakyThrows;
import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/list");

        try {
            List<TodoDto>  dtoList = TodoService.INSTANCE.listAll();
            req.setAttribute("list", dtoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }
}









