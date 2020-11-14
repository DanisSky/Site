package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.dto.CarDto;
import ru.itis.dto.CommentDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Comment;
import ru.itis.services.CarsService;
import ru.itis.services.CommentsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/carInfo")
public class CarInfoServlet extends HttpServlet {

    CarsService carsService;
    CommentsService commentsService;
    ObjectMapper objectMapper;
    private Long carId;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        carsService = applicationContext.getBean(CarsService.class);
        objectMapper = applicationContext.getBean(ObjectMapper.class);
        commentsService = applicationContext.getBean(CommentsService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carId = Long.parseLong(req.getParameter("carId"));
        Optional<CarDto> carDto = carsService.findById(carId);

        if (carDto.isPresent()) {
            List<CommentDto> commentDtos = commentsService.getAllCommentsByCarId(carDto.get().getId());

            req.setAttribute("Comments", commentDtos);
            req.setAttribute("Car", carDto.get());
            req.getRequestDispatcher("/WEB-INF/jsp/carInfo.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Comment comment = objectMapper.readValue(req.getReader(), Comment.class);

        UserDto userDto = (UserDto) req.getSession(false).getAttribute("userDto");

        comment.setUserId(userDto.getId());
        comment.setCarId(carId);

        commentsService.addComment(comment);

        List<CommentDto> commentDtos = commentsService.getAllCommentsByCarId(carId);
        String commentAsJson = objectMapper.writeValueAsString(commentDtos);

        System.out.println(commentAsJson);

        resp.setContentType("application/json");
        resp.getWriter().println(commentAsJson);
    }
}
