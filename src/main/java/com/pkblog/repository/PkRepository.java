package com.pkblog.repository;

import com.pkblog.entity.PkPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PkRepository extends JpaRepository<PkPost,Long> {
}
