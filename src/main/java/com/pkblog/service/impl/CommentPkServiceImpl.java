package com.pkblog.service.impl;

import com.pkblog.entity.Comment;
import com.pkblog.entity.PkPost;
import com.pkblog.exception.ResourceNotFoundException;
import com.pkblog.payload.CommentDto;
import com.pkblog.repository.CommentPkRepository;
import com.pkblog.repository.PkRepository;
import com.pkblog.service.CommentPkService;
import org.springframework.stereotype.Service;

@Service
public class CommentPkServiceImpl implements CommentPkService {

    private CommentPkRepository commentPkRepository;

    private PkRepository pkRepository;

    public CommentPkServiceImpl(CommentPkRepository commentPkRepository, PkRepository pkRepository) {
        this.commentPkRepository = commentPkRepository;
        this.pkRepository = pkRepository;
    }

    @Override
    public CommentDto createComment(long pkPostId, CommentDto commentDto) {
        PkPost post = pkRepository.findById(pkPostId).orElseThrow(
                () -> new ResourceNotFoundException("comment post is not found with id :" + pkPostId)
        );
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setPkPost(post);

        Comment saved = commentPkRepository.save(comment);

        commentDto.setName(saved.getName());
        commentDto.setEmail(saved.getEmail());
        commentDto.setBody(saved.getBody());
        return commentDto;


    }

    @Override
    public void deleteComment(long commentPkId) {
        commentPkRepository.deleteById(commentPkId);
    }


}
