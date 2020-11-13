package ru.itis.services;

import ru.itis.dto.CommentDto;
import ru.itis.models.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> getAllComments();
    void addComment(Comment comment);
    List<CommentDto> getAllCommentsByCarId(Long id);
}
