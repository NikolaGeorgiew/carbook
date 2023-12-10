package com.example.carbook.web;

import com.example.carbook.model.dto.BlogSummaryDTO;
import com.example.carbook.service.BlogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BlogService blogService;

    @Test
    void testBlogsPage() throws Exception {
        // Create sample data for the blog page
        List<BlogSummaryDTO> blogList = new ArrayList<>();
        // Add sample BlogSummaryDTO objects to the list

        // Mock the behavior of blogService.getAllBlogs to return the sample data
        when(blogService.getAllBlogs(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(blogList));

        // Perform an HTTP GET request to "/blog"
        ResultActions result = mockMvc.perform(get("/blog"));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "blog"
        result.andExpect(view().name("blog"));

        // Verify that the "blogs" attribute is added to the model with the correct data
        result.andExpect(model().attribute("blogs", hasProperty("content", is(blogList))));
    }
    @Test
    void testBlogDetailsPage() throws Exception {
        // Create sample data for the blog details page
        Long blogId = 1L;
        BlogSummaryDTO blogDetail = new BlogSummaryDTO(blogId,"","Sample title", "Sample description", "Sample second title", "Sample second description");
        Page<BlogSummaryDTO> allBlogsExcludingOne = new PageImpl<>(new ArrayList<>());

        // Mock the behavior of blogService.getBlogDetail to return the sample data
        when(blogService.getBlogDetail(blogId)).thenReturn(Optional.of(blogDetail));
        // Mock the behavior of blogService.getAllBlogsExcludingOne to return the sample data
        when(blogService.getAllBlogsExcludingOne(Mockito.any(Pageable.class), Mockito.eq(blogId)))
                .thenReturn(allBlogsExcludingOne);

        // Perform an HTTP GET request to "/blog/{id}"
        ResultActions result = mockMvc.perform(get("/blog/{id}", blogId));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "blog-single"
        result.andExpect(view().name("blog-single"));

        // Verify that the "blog" attribute is added to the model with the correct data
        result.andExpect(model().attribute("blog", is(blogDetail)));
        // Verify that the "blogs" attribute is added to the model with the correct data
        result.andExpect(model().attribute("blogs", hasProperty("content", is(allBlogsExcludingOne.getContent()))));
    }
}
