package org.zerock.w2.todo.fillter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Login Check Filter...........");

        // 로그인 정보가 세션에 존재하는지 여부를 판단
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if(session.getAttribute("loginInfo") == null){
            response.sendRedirect("/login");
            return;
        }

        // 다음필터 또는 서블릿으로 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
