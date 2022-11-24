package com.example.Reviewservice.Controller;

import com.example.Reviewservice.Dto.ReviewRequestDto;
import com.example.Reviewservice.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.add(reviewRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
