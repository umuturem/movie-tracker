package com.appfilm.appfilm.controller;

import com.appfilm.appfilm.dto.WatchListRequestDto;
import com.appfilm.appfilm.dto.WatchListResponseDto;
import com.appfilm.appfilm.service.WatchListFilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@CrossOrigin(origins = "http://localhost:5173")
public class WatchListFilmController {

    private final WatchListFilmService watchListFilmService;

    @Autowired
    public WatchListFilmController(WatchListFilmService watchListFilmService) {
        this.watchListFilmService = watchListFilmService;
    }

    @PostMapping
    public ResponseEntity<String> addToWatchList(@RequestBody WatchListRequestDto requestDto) {
        watchListFilmService.addToWatchList(requestDto);
        return ResponseEntity.ok("Film watchlist'e eklendi.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WatchListResponseDto>> getWatchListFilms(@PathVariable Long userId) {
        List<WatchListResponseDto> watchListFilms = watchListFilmService.getWatchListFilmsByUser(userId);
        return ResponseEntity.ok(watchListFilms);
    }

    @DeleteMapping("/{userId}/{filmId}")
    public ResponseEntity<String> removeFromWatchList(@PathVariable Long userId, @PathVariable Long filmId) {
        watchListFilmService.removeFromWatchList(userId, filmId);
        return ResponseEntity.ok("Film watchlist'ten kaldırıldı.");
    }
} 