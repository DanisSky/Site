package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.CarDto;
import ru.itis.services.CarsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class RootServlet extends HttpServlet {
    private CarsService carsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        carsService = applicationContext.getBean(CarsService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CarDto> cars = (ArrayList<CarDto>) carsService.getAllCars();
        req.setAttribute("carsForJsp", cars);
        req.getRequestDispatcher("/WEB-INF/jsp/root.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
