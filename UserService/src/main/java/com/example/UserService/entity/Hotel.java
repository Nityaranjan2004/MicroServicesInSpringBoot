package com.example.UserService.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;
}
