package com.example.carbook.service;

import com.example.carbook.model.dto.BlogSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Page<BlogSummaryDTO> getAllBlogs(Pageable pageable);

    Optional<BlogSummaryDTO> getBlogDetail(Long id);

    Page<BlogSummaryDTO> getAllBlogsExcludingOne(Pageable pageable, Long id);
}
