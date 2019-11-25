package com.training.salon.service;

import com.training.salon.entity.Comment;
import com.training.salon.entity.Master;
import com.training.salon.entity.User;
import com.training.salon.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllCommentsByMasterId(Long masterId) {
        return commentRepository.findAllByMasterIdOrderByDateTimeDesc(masterId);
    }

    public void createComment(String text, Master master, User user) {
        commentRepository.save(Comment.builder()
                .comment(text)
                .master(master)
                .user(user)
                .dateTime(LocalDateTime.now())
                .build());

    }
}
