package com.example.carbook.service.impl;

import com.example.carbook.model.dto.BlogSummaryDTO;
import com.example.carbook.model.entity.BlogEntity;
import com.example.carbook.repo.BlogRepository;
import com.example.carbook.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Page<BlogSummaryDTO> getAllBlogs(Pageable pageable) {
        return blogRepository
                .findAll(pageable)
                .map(BlogServiceImpl::mapAsSummary);

    }

    @Override
    public Optional<BlogSummaryDTO> getBlogDetail(Long id) {
        return blogRepository
                .findById(id)
                .map(BlogServiceImpl::mapAsSummary);
    }

    @Override
    public Page<BlogSummaryDTO> getAllBlogsExcludingOne(Pageable pageable, Long id) {
        return blogRepository
                .findAllByIdNot(pageable, id)
                .map(BlogServiceImpl::mapAsSummary);
    }

    private static BlogSummaryDTO mapAsSummary(BlogEntity blogEntity) {
        return new BlogSummaryDTO(blogEntity.getId(), blogEntity.getImageUrl(), blogEntity.getTitle(), blogEntity.getDescriptionFirstTitle(), blogEntity.getSecondTitle(), blogEntity.getDescriptionSecondTitle());
    }
}
