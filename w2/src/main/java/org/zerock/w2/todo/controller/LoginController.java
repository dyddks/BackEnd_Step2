package org.zerock.w2.todo.controller;

import lombok.extern.java.Log;
import org.zerock.w2.todo.dto.MemberDto;
import org.zerock.w2.todo.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

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

        String auto = req.getParameter("auto");
        boolean rememberMe = auto != null && auto.equals("on");


        MemberDto memberDto = null;

        try {
            memberDto = MemberService.INSTANCE.login(mid, mpw);

            if(rememberMe){
                String uuid = UUID.randomUUID().toString();
                MemberService.INSTANCE.updateUuid(mid, uuid);

                memberDto.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*7);
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);
            }
        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");
        }

        HttpSession session = req.getSession();
        session.setAttribute("loginInfo", memberDto);

        resp.sendRedirect("/todo/list");
    }
}