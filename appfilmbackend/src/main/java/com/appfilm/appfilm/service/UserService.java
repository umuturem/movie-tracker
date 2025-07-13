package com.appfilm.appfilm.service;

import com.appfilm.appfilm.dto.AuthRequestDto;
import com.appfilm.appfilm.dto.AuthResponseDto;

public interface UserService {
    AuthResponseDto login(AuthRequestDto requestDto);
}

