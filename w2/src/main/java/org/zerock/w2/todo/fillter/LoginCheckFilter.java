package org.zerock.w2.todo.fillter;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.todo.dto.MemberDto;
import org.zerock.w2.todo.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("Login Check Filter...........");

        // 로그인 정보가 세션에 존재하는지 여부를 판단
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if(session.getAttribute("loginInfo") != null){
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        Cookie cookie = findCookie(request.getCookies(), "remember-me");

        if(cookie == null){
            response.sendRedirect("/login");
            return;
        }

        log.info("cookie는 존재하는 상황");

        String uuid = cookie.getValue();

        try{
            MemberDto memberDto = MemberService.INSTANCE.getByUUID(uuid);
            if(memberDto == null){
                throw new Exception("Cookie value is not valid");
            }
            session.setAttribute("loginInfo", memberDto);
            chain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/login");
        }

        // 다음필터 또는 서블릿으로 전달
        chain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        if(cookies == null && cookies.length == 0){
            return null;
        }

        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst();

        return result.isPresent() ? result.get() : null;
    }
}
