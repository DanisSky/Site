package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.FileInfo;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/file")
public class FilesServlet extends HttpServlet {

    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        fileService = applicationContext.getBean(FileService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String fileId = req.getParameter("id");

        Optional<FileInfo> fileInfo = fileService.getFileInfo(Long.parseLong(fileId));
        if (fileInfo.isPresent()) {
            response.setContentType(fileInfo.get().getType());
            response.setContentLength(fileInfo.get().getSize().intValue());
            response.setHeader("Content-Disposition", "filename=\"" + fileInfo.get().getOriginalFileName() + "\"");

            fileService.writeFileFromStorage(Long.parseLong(fileId), response.getOutputStream());
            response.flushBuffer();
        }
    }
}
