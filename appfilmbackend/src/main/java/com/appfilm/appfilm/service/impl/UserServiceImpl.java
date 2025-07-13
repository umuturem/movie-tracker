package com.appfilm.appfilm.service.impl;

import com.appfilm.appfilm.dto.AuthRequestDto;
import com.appfilm.appfilm.dto.AuthResponseDto;
import com.appfilm.appfilm.entity.User;
import com.appfilm.appfilm.repository.UserRepository;
import com.appfilm.appfilm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired // Spring otomatik olarak bu bağımlılığı enjekte eder
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto requestDto) {
        Optional<User> userOpt = userRepository.findByUsername(requestDto.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(requestDto.getPassword())) {
                // Giriş başarılı, DTO döndür
                return new AuthResponseDto(user.getId(), user.getUsername());
            }
        }

        // Giriş başarısızsa null dönebiliriz (frontend kontrol eder)
        return null;
    }
}
