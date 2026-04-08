package com.example.UserService.external.service;

import com.example.UserService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;
import java.util.Objects;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
    @PostMapping("/rating")
    public Rating createRating(Rating values);


    //if we want to add putmapping in our prject we build it like that
//    @PutMapping
//    public Rating UpdateRating(@PathVariable String ratingId,Rating rating);

//    @DeleteMapping("/ratig/{ratingId}")
//    public void deleteRating(@PathVariable String ratingId);
}
