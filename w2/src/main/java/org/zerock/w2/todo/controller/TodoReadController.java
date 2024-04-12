package org.zerock.w2.todo.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.todo.dto.TodoDto;
import org.zerock.w2.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/read");

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDto dto = TodoService.INSTANCE.listOne(tno);
            req.setAttribute("dto", dto);

            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListstr = viewTodoCookie.getValue();
            boolean exist = false;

            if(todoListstr != null && todoListstr.indexOf(tno+"-") >= 0){
                exist = true;
            }

            log.info("exist: " + exist);

            if(!exist){
                todoListstr += tno + "-";
                viewTodoCookie.setValue(todoListstr);
                viewTodoCookie.setMaxAge(60* 60* 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        Cookie targetCookie = null;
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    targetCookie = cookie;
                    break;
                }
            }
        }
        if(targetCookie == null){
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }
}
