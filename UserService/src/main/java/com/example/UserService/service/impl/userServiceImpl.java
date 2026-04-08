package com.example.UserService.service.impl;

import com.example.UserService.entity.Hotel;
import com.example.UserService.entity.Rating;
import com.example.UserService.entity.User;
import com.example.UserService.exception.ResourceNotFoundException;
import com.example.UserService.external.service.HotelService;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(userServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userId every time
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id not found on the server"+userId));
        //fetch rating of the above user from rating service
        //in ratingservice there is an api where it fetch user rating
        //@GetMapping("/user/{userId}")
        Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATINGSERVICE/rating/user/"+user.getUserId(), Rating[].class);
        logger.info("{}"+ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get all hotel
            //ResponseEntity<Hotel>forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
