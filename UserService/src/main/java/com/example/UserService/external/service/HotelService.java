package com.example.UserService.external.service;

import com.example.UserService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HotelService")
public interface HotelService {

    @GetMapping("/hotels/{hotelid}")
    Hotel getHotel(@PathVariable String hotelid);


}
