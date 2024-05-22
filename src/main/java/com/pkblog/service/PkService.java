package com.pkblog.service;

import com.pkblog.payload.PkDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PkService {
    void createPkPost(PkDto pkDto);
   void deletePkPost(@PathVariable long id);
   List<PkDto> getAllPkPosts(int pageNo, int pageSize, String sortBy, String sortDir);
   PkDto updatePkPost(long id,PkDto pkDto);
}
