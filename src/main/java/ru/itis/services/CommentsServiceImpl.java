package ru.itis.services;

import ru.itis.dto.CommentDto;
import ru.itis.models.Comment;
import ru.itis.repositories.CommentsRepository;

import java.util.List;

public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentsRepository.findAll();
    }

    @Override
    public void addComment(Comment comment) {
        commentsRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAllCommentsByCarId(Long id) {

        return commentsRepository.findAllByCarId(id);
    }
}
