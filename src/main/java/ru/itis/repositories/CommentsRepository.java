package ru.itis.repositories;

import ru.itis.dto.CommentDto;
import ru.itis.models.Comment;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment> {
    List<CommentDto> findAllByCarId(Long id);
}
