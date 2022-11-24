package com.example.Reviewservice.Dao;

import com.example.Reviewservice.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewDao extends MongoRepository<Review, String> {
}
