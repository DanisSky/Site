package ru.itis.listeners;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.models.Comment;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class AppConfigServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.jdbc.driver-class-name"));
        hikariConfig.setUsername(properties.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(properties.getProperty("db.jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);
        ChangePasswordService changePasswordService = new ChangePasswordServiceImpl(usersRepository);

        CarsRepository carsRepository = new CarsRepositoryJdbcTemplateImpl(dataSource);
        CarsService carsService = new CarsServiceImpl(carsRepository);

        CommentsRepository commentsRepository = new CommentsRepositoryTemplateImpl(dataSource);
        CommentsService commentsService = new CommentsServiceImpl(commentsRepository);

        servletContext.setAttribute("carsService",carsService);
        servletContext.setAttribute("dataSource", dataSource);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("changePasswordService",changePasswordService);
        servletContext.setAttribute("commentsService", commentsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}