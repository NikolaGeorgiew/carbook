package com.example.carbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "blogs")
public class BlogEntity extends BaseEntity{

    @Column(nullable = false)
    private String title;
    @Column(name = "description_first_title", nullable = false)
    private String descriptionFirstTitle;

    @Column(name = "second_title")
    private String secondTitle;
    @Column(name = "description_second_title", nullable = false)
    private String descriptionSecondTitle;


//    @OneToMany(mappedBy = "blog")
//    private List<CommentEntity> comments;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescriptionFirstTitle() {
        return descriptionFirstTitle;
    }

    public void setDescriptionFirstTitle(String descriptionFirstTitle) {
        this.descriptionFirstTitle = descriptionFirstTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getDescriptionSecondTitle() {
        return descriptionSecondTitle;
    }

    public void setDescriptionSecondTitle(String descriptionSecondTitle) {
        this.descriptionSecondTitle = descriptionSecondTitle;
    }
}
