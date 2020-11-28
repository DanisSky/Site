package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Car;
import ru.itis.models.Mark;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Optional;

public class CarsRepositoryJdbcTemplateImpl implements CarsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT car.id, m.mark, model, price, description, mileage, storage_file_name, type from car\n" +
            "join marks m on car.mark = m.id\n" +
            "join file  on car.file_id = file.id order by car.id;";

    private static final String SQL_SELECT_BY_ID = "select car.id, m.mark, model, price, description, mileage, storage_file_name, type from car join marks m on car.mark = m.id join file on car.id = file.id where car.id=?";
    private static final String SQL_SELECT_MARK_ID_BY_NAME = "select * from marks where mark=?";
    //language=SQL
    private static final String SQL_INSERT = "insert into car ( mark, model, price, description, mileage, file_id) values (?,?,?,?,?,?)";


    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Car> carRowMapper = (row, rowNumber) -> Car.builder()
            .id(row.getLong("id"))
            .mark(row.getString("mark"))
            .model(row.getString("model"))
            .price(row.getDouble("price"))
            .description(row.getString("description"))
            .mileage(row.getLong("mileage"))
            .storageFileName(row.getString("storage_file_name"))
            .fileType(row.getString("type"))
            .build();

    private final RowMapper<Mark> markRowMapper = (row, rowNumber) -> Mark.builder()
            .id(row.getLong("id"))
            .mark(row.getString("mark"))
            .build();

    public CarsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Car entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getMarkId(),
                entity.getModel(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getMileage(),
                entity.getFileId());
    }

    @Override
    public void update(Car entity) {

    }

    @Override
    public void delete(Car entity) {

    }

    @Override
    public Optional<Car> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, carRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public ArrayList<Car> findAll() {
        return (ArrayList<Car>) jdbcTemplate.query(SQL_SELECT_ALL, carRowMapper);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Mark> findMarkIdByName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_MARK_ID_BY_NAME, markRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
