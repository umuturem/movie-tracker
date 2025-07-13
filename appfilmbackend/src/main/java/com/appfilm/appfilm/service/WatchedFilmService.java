package com.appfilm.appfilm.service;

import com.appfilm.appfilm.dto.WatchedRequestDto;
import com.appfilm.appfilm.dto.WatchedResponseDto;

import java.util.List;

public interface WatchedFilmService {

    void addToWatched(WatchedRequestDto requestDto);

    List<WatchedResponseDto> getWatchedFilmsByUser(Long userId);

    void removeFromWatched(Long userId, Long filmId);
}

