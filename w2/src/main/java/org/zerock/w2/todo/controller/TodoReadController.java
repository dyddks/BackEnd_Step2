package org.zerock.w2.todo.controller;

import org.zerock.w2.todo.dto.TodoDto;
import org.zerock.w2.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/read");

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDto dto = TodoService.INSTANCE.listOne(tno);
            req.setAttribute("dto", dto);
        } catch (Exception e) {}


        req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
    }
}
