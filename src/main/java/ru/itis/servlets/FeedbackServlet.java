package ru.itis.servlets;

import ru.itis.dto.FeedbackForm;
import ru.itis.services.FeedbackService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private FeedbackService feedbackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        feedbackService = (FeedbackService) servletContext.getAttribute("feedbackService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FeedbackForm feedbackForm = FeedbackForm.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .text(req.getParameter("message"))
                .build();
        feedbackService.sendFeedback(feedbackForm);
        resp.sendRedirect("/");
    }
}
