package org.zerock.w2.todo.controller;

import lombok.extern.java.Log;
import org.zerock.w2.todo.dto.MemberDto;
import org.zerock.w2.todo.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
@Log
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login get...................");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login post..................");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        MemberDto memberDto = null;

        try {
            memberDto = MemberService.INSTANCE.login(mid, mpw);
        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");
        }

        HttpSession session = req.getSession();
        session.setAttribute("loginInfo", memberDto);

        resp.sendRedirect("/todo/list");
    }
}