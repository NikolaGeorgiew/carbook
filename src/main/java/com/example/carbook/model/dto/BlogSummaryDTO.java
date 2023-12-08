package com.example.carbook.model.dto;

public record BlogSummaryDTO(
        Long id,
        String imageUrl,
        String title,
        String descriptionFirstTitle,
        String secondTitle,
        String descriptionSecondTitle
) {
}
