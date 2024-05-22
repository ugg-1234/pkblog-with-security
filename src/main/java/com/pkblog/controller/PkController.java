package com.pkblog.controller;

import com.pkblog.payload.PkDto;
import com.pkblog.service.PkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PkController {
    private PkService pkService;

    public PkController(PkService pkService) {
        this.pkService = pkService;
    }

    //http://localhost:8080/api/post
//    @PostMapping
//    public ResponseEntity<String > createPkPost(@RequestBody PkDto pkDto){
//        pkService.createPkPost(pkDto);
//        return new ResponseEntity<>("pk post is created successfully", HttpStatus.CREATED);
//    }
        //http://localhost:8080/api/post/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePkPost(@PathVariable long id){
        pkService.deletePkPost(id);
        return new ResponseEntity<>("pk post is deleted successfully",HttpStatus.OK);
    }

    //http://localhost:8080/api/post
    @PostMapping
    public ResponseEntity<? > createPkPost(@Valid  @RequestBody PkDto pkDto, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
        pkService.createPkPost(pkDto);
        return new ResponseEntity<>("pk post is created successfully", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/post
//    @GetMapping
//    public ResponseEntity<List<PkDto>> getAllPkPosts(){
//        List<PkDto> allPkPosts = pkService.getAllPkPosts();
//        return new ResponseEntity<>(allPkPosts,HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<PkDto>> getAllPkPosts(
          @RequestParam(name = "pageNo",defaultValue = "0",required = false)  int pageNo,
          @RequestParam(name = "pageSize",defaultValue = "3",required = false)   int pageSize,
          @RequestParam(name = "sortBy",defaultValue = "id",required = false)   String sortBy,
          @RequestParam(name = "sortDir",defaultValue = "asc",required = false)  String sortDir
    ){
        List<PkDto> allPkPosts = pkService.getAllPkPosts(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allPkPosts,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PkDto> updatePkPost(
            @RequestParam(name="id") long id,@RequestBody PkDto pkDto){
        PkDto updatePkPost = pkService.updatePkPost(id, pkDto);
        return new ResponseEntity<>(updatePkPost,HttpStatus.OK);
    }
}
