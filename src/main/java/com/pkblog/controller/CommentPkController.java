package com.pkblog.controller;

import com.pkblog.payload.CommentDto;
import com.pkblog.service.CommentPkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentPkController {
    private CommentPkService commentPkService;

    public CommentPkController(CommentPkService commentPkService) {
        this.commentPkService = commentPkService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam(name="pkPostId") long pkPostId, @RequestBody CommentDto commentDto){
        CommentDto comment = commentPkService.createComment(pkPostId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentPkId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentPkId){
        commentPkService.deleteComment(commentPkId);
        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }
}
