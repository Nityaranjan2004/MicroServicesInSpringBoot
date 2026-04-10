package com.example.UserService.controller;

import com.example.UserService.entity.User;
import com.example.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createuser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    int retryCount=1;
    @GetMapping("/{userId}")
 //   @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
//      @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack")
   // @RateLimiter(name = "userRatelimitter",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingelUser(@PathVariable String userId){
        log.info("retry Count:{}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<User> ratingHotelFallBack(String userId,Exception ex){     //ratingHotelFallBack return type = getSingelUser returntype
        //log.info("Fallback is executed because service is down:",ex.getMessage());

        ex.printStackTrace();
        User user = User.builder()
                .email("asd@gmail.com")
                .name("dummy")
                .about("this user is created")
                .userId("123")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }





    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allusers= userService.getAllUser();
        return ResponseEntity.ok(allusers);
    }
}
