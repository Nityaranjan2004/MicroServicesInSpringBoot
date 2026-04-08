package com.example.UserService.exception;

import com.example.UserService.payload.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class GlobalExceptionHandeler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponce> handelResourceNotFoundException(ResourceNotFoundException ex){
        String messaage = ex.getMessage();
        ApiResponce responce = ApiResponce.builder()
                .message(messaage)
                .success(true)
                .build();
        return new ResponseEntity<ApiResponce>(responce,HttpStatus.NOT_FOUND);
    }
}
