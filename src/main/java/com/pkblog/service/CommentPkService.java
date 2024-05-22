package com.pkblog.service;

import com.pkblog.payload.CommentDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface CommentPkService {

    CommentDto createComment(long pkPostId,CommentDto commentDto);
    void deleteComment(long commentPkId);

}
