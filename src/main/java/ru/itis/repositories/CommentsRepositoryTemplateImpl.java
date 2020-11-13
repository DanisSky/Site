package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dto.CommentDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Comment;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentsRepositoryTemplateImpl implements CommentsRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * from comment ";
    private static final String SQL_INSERT = "insert into comment(user_id, text, car_id) VALUES (?,?,?)";
    private static final String SQL_SELECT_ALL_BY_CAR_ID = "select comment.id c_id, text, data, a.id a_id, first_name, last_name from comment join account as a on comment.user_id = a.id where car_id=? order by data";

    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Comment> commentRowMapper = (row, rowNumber) -> Comment.builder()
            .id(row.getLong("id"))
            .carId(row.getLong("car_id"))
            .data(row.getString("data"))
            .text(row.getString("text"))
            .userId(row.getLong("user_id"))
            .build();

    private final RowMapper<CommentDto> commentDtoRowMapper = (row, rowNumber) -> CommentDto.builder()
            .id(row.getLong("c_id"))
            .text(row.getString("text"))
            .data(row.getString("data"))
            .userDto(UserDto.builder()
                    .id(row.getLong("a_id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .build())
            .build();

    public CommentsRepositoryTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Comment entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getUserId(),
                entity.getText(),
                entity.getCarId());
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(Comment entity) {

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Comment> findAll() {
        return (ArrayList<Comment>) jdbcTemplate.query(SQL_SELECT_ALL, commentRowMapper);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<CommentDto> findAllByCarId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_CAR_ID, commentDtoRowMapper, id);
    }
}
