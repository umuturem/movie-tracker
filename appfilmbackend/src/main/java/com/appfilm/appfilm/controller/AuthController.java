package com.appfilm.appfilm.controller;

import com.appfilm.appfilm.dto.AuthRequestDto;
import com.appfilm.appfilm.dto.AuthResponseDto;
import com.appfilm.appfilm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Kullanıcı girişi (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto requestDto) {
        AuthResponseDto responseDto = userService.login(requestDto);

        if (responseDto != null) {
            return ResponseEntity.ok(responseDto); // 200 OK + kullanıcı bilgisi
        } else {
            return ResponseEntity.status(401).body("Kullanıcı adı veya şifre yanlış");
        }
    }
}

