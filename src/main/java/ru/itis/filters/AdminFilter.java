package ru.itis.filters;

import ru.itis.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        UserDto userDto = null;

        if (sessionExists) {
            userDto = (UserDto) session.getAttribute("userDto");
            if (userDto != null) {
                isAuthenticated = true;
            }
        }

        if (isAuthenticated) {
            if (userDto.getRole().equals("admin")){
                filterChain.doFilter(request,response);
            }else {
                response.sendRedirect("/");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
