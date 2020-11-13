package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * from account";
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO account (first_name, last_name, hash_password, email, phone) VALUES (?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM  account WHERE email=?";
    //language=SQL
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM account WHERE id=?";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select *from account where id=?";
    //language=SQL
    private static final String SQL_CHANGE_PASSWORD_BY_ID = "update account set hash_password=? where id=?";

    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hash_password"))
            .phone(row.getString("phone"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }


    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT,
                user.getFirstName(),
                user.getLastName(),
                user.getHashPassword(),
                user.getEmail(),
                user.getPhone());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updatePassword(Long id, String hashPassword) {
        jdbcTemplate.update(SQL_CHANGE_PASSWORD_BY_ID, hashPassword, id);
    }


    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, id);
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public ArrayList<User> findAllByName(String name) {
        return null;
    }

}
