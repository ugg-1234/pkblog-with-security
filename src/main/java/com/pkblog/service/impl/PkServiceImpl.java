package com.pkblog.service.impl;

import com.pkblog.entity.PkPost;
import com.pkblog.exception.ResourceNotFoundException;
import com.pkblog.payload.PkDto;
import com.pkblog.repository.PkRepository;
import com.pkblog.service.PkService;
import org.apache.coyote.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PkServiceImpl implements PkService {

  private   PkRepository pkRepository;

    public PkServiceImpl(PkRepository pkRepository) {
        this.pkRepository = pkRepository;
    }

    @Override
    public void createPkPost(PkDto pkDto) {
        PkPost pkPost = new PkPost();
        pkPost.setPkName(pkDto.getPkName());
        pkPost.setEmail(pkDto.getEmail());
        pkPost.setPkSurname(pkDto.getPkSurname());
        pkPost.setMobile(pkDto.getMobile());
        pkRepository.save(pkPost);
    }

    @Override
    public void deletePkPost(long id) {
        Optional<PkPost> byId = pkRepository.findById(id);
        if(byId.isPresent()){
            pkRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("pk post is not found with id:"+ id);

    }

    @Override
    public List<PkDto> getAllPkPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<PkPost> pagePost = pkRepository.findAll(pageable);
        List<PkPost> allPkPosts = pagePost.getContent();
        List<PkDto> collect = allPkPosts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PkDto updatePkPost(long id, PkDto pkDto) {
        PkPost post = pkRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post is not found with id"+id));


        post.setPkName(pkDto.getPkName());
        post.setPkSurname(pkDto.getPkSurname());
        post.setEmail(pkDto.getEmail());
        post.setMobile(pkDto.getMobile());
        PkPost savedPost = pkRepository.save(post);
        PkDto dto = mapToDto(savedPost);
        return dto;
    }

    PkDto mapToDto(PkPost pkPost) {
        PkDto pkDto = new PkDto();
        pkDto.setPkName(pkPost.getPkName());
        pkDto.setPkSurname(pkPost.getPkSurname());
        pkDto.setEmail(pkPost.getEmail());
        pkDto.setMobile(pkPost.getMobile());
       return pkDto;
    }
}
