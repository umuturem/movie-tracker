package com.appfilm.appfilm.service;

import com.appfilm.appfilm.dto.WatchListRequestDto;
import com.appfilm.appfilm.dto.WatchListResponseDto;

import java.util.List;

public interface WatchListFilmService {

    void addToWatchList(WatchListRequestDto requestDto);

    List<WatchListResponseDto> getWatchListFilmsByUser(Long userId);

    void removeFromWatchList(Long userId, Long filmId);
} 