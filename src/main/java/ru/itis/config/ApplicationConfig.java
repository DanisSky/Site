package ru.itis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "ru.itis")

public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository());
    }

    @Bean
    public CarsService carsService() {
        return new CarsServiceImpl(carsRepository());
    }

    @Bean
    public SignInService signInService() {
        return new SignInServiceImpl(usersRepository());
    }

    @Bean
    public SignUpService signUpService() {
        return new SignUpServiceImpl(usersRepository());
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        return new ChangePasswordServiceImpl(usersRepository());
    }

    @Bean
    public CommentsService commentsService() {
        return new CommentsServiceImpl(commentsRepository());
    }

    @Bean
    public FeedbackService feedbackService() {
        return new FeedbackServiceImpl(feedbackRepository());
    }

    @Bean
    public FileService fileService() {
        return new FileServiceImpl(filesRepository());
    }

    @Bean
    public FilesRepository filesRepository() {
        return new FilesRepositoryImpl(dataSource());
    }

    @Bean
    CarsRepository carsRepository() {
        return new CarsRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public CommentsRepository commentsRepository() {
        return new CommentsRepositoryTemplateImpl(dataSource());
    }

    @Bean
    public FeedbackRepository feedbackRepository() {
        return new FeedbackRepositoryImpl(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean // создали bean с id = objectMapper
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver-class-name"));
        return hikariConfig;
    }
}
