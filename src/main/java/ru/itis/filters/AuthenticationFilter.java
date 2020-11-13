package ru.itis.filters;

import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isLoginPage = request.getRequestURI().equals("/signIn");
        Boolean isSignUpPage = request.getRequestURI().equals("/signUp");
        if (sessionExists) {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            if (userDto != null) {
                isAuthenticated = true;
            }
        }
        if (isAuthenticated) {
            if (isLoginPage || isSignUpPage) {
                response.sendRedirect("/");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            if (isLoginPage || isSignUpPage) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/signUp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
