package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Feedback;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Optional;

public class FeedbackRepositoryImpl implements FeedbackRepository {

    //language=SQL
    private static final String SQL_INSERT = "insert into feedback (name, email, text) VALUES (?,?,?)";
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Feedback> feedbackRowMapper = (row, rowNumber) -> Feedback.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .email(row.getString("email"))
            .text(row.getString("text"))
            .build();

    public FeedbackRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Feedback entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getName(),
                entity.getEmail(),
                entity.getText());
    }

    @Override
    public void update(Feedback entity) {

    }

    @Override
    public void delete(Feedback entity) {

    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Feedback> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
