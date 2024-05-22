package com.pkblog.repository;

import com.pkblog.entity.Comment;
import com.pkblog.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPkRepository extends JpaRepository<Comment,Long> {

}
