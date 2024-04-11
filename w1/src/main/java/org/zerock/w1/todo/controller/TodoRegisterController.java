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

@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("입력화면을 볼 수 있도록 구성");

        // 입력 할 수있는 jsp로 forward
        // jsp에서는 다시 "/todo/register" post 포스트 요청을 보내면 DB에 등록
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/todo/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/register");


        System.out.println("DB에 req의 parameter를 꺼내어 저장");
        TodoDto dto = TodoDto.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("date")))
                .build();
        try{
            TodoService.INSTANCE.register(dto);
        }catch (Exception e){

        }

        resp.sendRedirect("/todo/list");
    }
}
