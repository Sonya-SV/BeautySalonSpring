package com.training.salon.repository;

import com.training.salon.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMasterIdOrderByDateTimeDesc(Long masterId);
}
