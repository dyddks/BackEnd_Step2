package org.zerock.w2.todo.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.todo.dto.TodoDto;
import org.zerock.w2.todo.service.TodoService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name="todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/list");

        ServletContext context = req.getServletContext();

        log.info("appName: " + context.getAttribute("appName"));

        try {
            List<TodoDto>  dtoList = TodoService.INSTANCE.listAll();
            req.setAttribute("list", dtoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }
}









