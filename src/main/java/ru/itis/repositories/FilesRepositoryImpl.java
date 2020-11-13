package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Optional;

public class FilesRepositoryImpl implements FilesRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into file(storage_file_name, original_file_name, type, size) " +
            "values (?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_ID = "select * from file where id = ?";

    private JdbcTemplate jdbcTemplate;


    public FilesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    private final RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();


    @Override
    public void save(FileInfo entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getStorageFileName(), entity.getOriginalFileName(),
                entity.getType(),
                entity.getSize());

    }

    @Override
    public void update(FileInfo entity) {

    }

    @Override
    public void delete(FileInfo entity) {

    }

    @Override
    public Optional<FileInfo> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public ArrayList<FileInfo> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
