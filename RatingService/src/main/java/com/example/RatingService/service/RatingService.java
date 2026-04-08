package com.example.RatingService.service;

import com.example.RatingService.RatingServiceApplication;
import com.example.RatingService.entity.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getAllRating();
    List<Rating> getReatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String holelId);

}
