package com.example.carbook.web;

import com.example.carbook.model.dto.BlogSummaryDTO;
import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.service.BlogService;
import com.example.carbook.service.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String blogs(Model model, @PageableDefault(
            sort = "id"
    ) Pageable pageable) {
        Page<BlogSummaryDTO> allBlogs = blogService.getAllBlogs(pageable);

        model.addAttribute("blogs", allBlogs);
        return "blog";
    }
    @GetMapping("/blog/{id}")
    public String details(@PathVariable("id") Long id, Model model, @PageableDefault(size = 3) Pageable pageable) {
        BlogSummaryDTO blogSummaryDTO = blogService
                .getBlogDetail(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object with id " + id + " was not found!"));

        Page<BlogSummaryDTO> allBlogsExcludingOne = blogService.getAllBlogsExcludingOne(pageable, id);

        model.addAttribute("blog", blogSummaryDTO);
        model.addAttribute("blogs", allBlogsExcludingOne);
        return "blog-single";
    }

}
