package com.example.HotelService.controller;


import com.example.HotelService.entity.Hotel;
import com.example.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotels= hotelService.getAll();
        return ResponseEntity.ok(hotels);
    }


    @GetMapping("/{hotelid}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelid){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelid));
    }

}
