package com.example.Reviewservice.Service;

import com.example.Reviewservice.Adapter.ReviewAdapter;
import com.example.Reviewservice.Dao.ReviewDao;
import com.example.Reviewservice.Dto.ReviewRequestDto;
import com.example.Reviewservice.Message.Sender;
import com.example.Reviewservice.Model.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private Sender sender;
    private ObjectMapper mapper = new ObjectMapper();
    ReviewAdapter reviewAdapter = new ReviewAdapter();
    public void add(ReviewRequestDto reviewRequestDto) {
        Review review = reviewAdapter.reviewRequestDtoToReview(reviewRequestDto);
        reviewDao.save(review);
        String reviewStr = null;
        try {
            reviewStr = mapper.writeValueAsString(reviewRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sender.sendToBookQueryAdd(reviewStr);
    }

}
