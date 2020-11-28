package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.Car;
import ru.itis.services.CarsService;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/admin")
@MultipartConfig
public class AdminServlet extends HttpServlet {

    private CarsService carsService;
    private FileService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        carsService = applicationContext.getBean(CarsService.class);
        filesService = applicationContext.getBean(FileService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("photo");
        Long id = filesService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());

        Car car = Car.builder()
                .fileId(id)
                .model(req.getParameter("model"))
                .price(Double.valueOf(req.getParameter("price")))
                .description(req.getParameter("description"))
                .mileage(Long.valueOf(req.getParameter("mileage")))
                .markId(carsService.findByMark(req.getParameter("mark")))
                .build();

        carsService.save(car);
        resp.sendRedirect("/");
    }
}
