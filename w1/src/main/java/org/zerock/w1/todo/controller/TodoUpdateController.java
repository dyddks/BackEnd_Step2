package org.zerock.w1.todo.controller;

import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoUpdateController", urlPatterns = "/todo/update")
public class TodoUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDto dto = TodoService.INSTANCE.listOne(tno);
            req.setAttribute("dto", dto);
        }catch (Exception e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/todo/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String finishedStr = req.getParameter("finished");

        TodoDto dto = TodoDto.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("date")))
                .finished(finishedStr != null && finishedStr.equals("on"))
                .build();
        try{
            TodoService.INSTANCE.update(dto);
        }catch (Exception e){

        }

        resp.sendRedirect("/todo/list");
    }
}
