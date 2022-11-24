package com.example.Reviewservice.Adapter;

import com.example.Reviewservice.Dto.ReviewRequestDto;
import com.example.Reviewservice.Model.Review;

public class ReviewAdapter {

    public Review reviewRequestDtoToReview(ReviewRequestDto reviewRequestDto) {
        return Review.builder()
                .rating(reviewRequestDto.getRating())
                .customerName(reviewRequestDto.getCustomerName())
                .description(reviewRequestDto.getDescription())
                .isbn(reviewRequestDto.getIsbn())
                .build();
    }
}
