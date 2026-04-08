package com.example.HotelService.service;

import com.example.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create (Hotel hotel);

    List<Hotel> getAll();

    Hotel getHotelById (String id);
}
