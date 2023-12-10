package com.example.carbook.service.impl;

import com.example.carbook.model.dto.BlogSummaryDTO;
import com.example.carbook.model.entity.BlogEntity;
import com.example.carbook.repo.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlogServiceImplTest {
    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogServiceImpl blogService;

    @Test
    void testGetAllBlogs() {
        // Create sample data
        List<BlogEntity> blogEntities = new ArrayList<>();
        // Add sample blog entities to the list

        // Create a Page object from the list
        Page<BlogEntity> blogPage = new PageImpl<>(blogEntities);

        // Mock the behavior of blogRepository.findAll
        when(blogRepository.findAll(Mockito.any(Pageable.class))).thenReturn(blogPage);

        // Invoke the method under test
        Page<BlogSummaryDTO> result = blogService.getAllBlogs(Pageable.unpaged());

        // Assertions
        // and the structure of your entities and DTOs
        assertEquals(blogPage.getTotalElements(), result.getTotalElements());
    }
    @Test
    void testGetBlogDetailWhenBlogExists() {
        // Create a sample blog entity
        BlogEntity blogEntity = new BlogEntity();
        // Set properties on the blog entity

        // Mock the behavior of blogRepository.findById
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blogEntity));

        // Invoke the method under test
        Optional<BlogSummaryDTO> result = blogService.getBlogDetail(1L);

        // Assertions
        assertTrue(result.isPresent());
    }

    @Test
    void testGetBlogDetailWhenBlogDoesNotExist() {
        // Mock the behavior of blogRepository.findById for a non-existent blog
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());

        // Invoke the method under test
        Optional<BlogSummaryDTO> result = blogService.getBlogDetail(1L);

        // Assertions
        assertFalse(result.isPresent());
    }
    @Test
    void testGetAllBlogsExcludingOne() {
        // Create sample data
        List<BlogEntity> blogEntities = new ArrayList<>();
        // Add sample blog entities to the list

        // Create a Page object from the list
        Page<BlogEntity> blogPage = new PageImpl<>(blogEntities);

        // Mock the behavior of blogRepository.findAllByIdNot
        when(blogRepository.findAllByIdNot(Mockito.any(Pageable.class), Mockito.eq(1L))).thenReturn(blogPage);

        // Invoke the method under test
        Page<BlogSummaryDTO> result = blogService.getAllBlogsExcludingOne(Pageable.unpaged(), 1L);

        // Assertions
        assertEquals(blogPage.getTotalElements(), result.getTotalElements());
    }
    @Test
    void testMapAsSummary() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Create a sample blog entity
        BlogEntity blogEntity = new BlogEntity();
        // Set properties on the blog entity

        // Get the mapAsSummary method using reflection
        Method mapAsSummaryMethod = BlogServiceImpl.class.getDeclaredMethod("mapAsSummary", BlogEntity.class);
        mapAsSummaryMethod.setAccessible(true);  // Make the private method accessible

        // Invoke the method under test
        BlogSummaryDTO result = (BlogSummaryDTO) mapAsSummaryMethod.invoke(null, blogEntity);

        // Assertions
        assertEquals(blogEntity.getId(), result.id());
        assertEquals(blogEntity.getImageUrl(), result.imageUrl());
        assertEquals(blogEntity.getTitle(), result.title());
        assertEquals(blogEntity.getDescriptionFirstTitle(), result.descriptionFirstTitle());
        assertEquals(blogEntity.getSecondTitle(), result.secondTitle());
        assertEquals(blogEntity.getDescriptionSecondTitle(), result.descriptionSecondTitle());
    }
}
