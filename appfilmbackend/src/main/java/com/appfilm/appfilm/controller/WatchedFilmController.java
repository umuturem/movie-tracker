package com.appfilm.appfilm.controller;

import com.appfilm.appfilm.dto.WatchedRequestDto;
import com.appfilm.appfilm.dto.WatchedResponseDto;
import com.appfilm.appfilm.service.WatchedFilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watched")
public class WatchedFilmController {

    private final WatchedFilmService watchedFilmService;

    @Autowired
    public WatchedFilmController(WatchedFilmService watchedFilmService) {
        this.watchedFilmService = watchedFilmService;
    }

    /**
     * Film izlenmiş olarak işaretlenir (eklenir)
     * POST /api/watched
     */
    @PostMapping
    public ResponseEntity<String> addToWatched(@RequestBody WatchedRequestDto requestDto) {
        watchedFilmService.addToWatched(requestDto);
        return ResponseEntity.ok("Film watched list'e eklendi.");
    }

    /**
     * Belirli bir kullanıcıya ait izlenmiş filmleri getirir
     * GET /api/watched/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<WatchedResponseDto>> getWatchedFilms(@PathVariable Long userId) {
        List<WatchedResponseDto> watchedFilms = watchedFilmService.getWatchedFilmsByUser(userId);
        return ResponseEntity.ok(watchedFilms);
    }

    /**
     * Kullanıcının izlenmiş listesinden bir filmi siler
     * DELETE /api/watched/{userId}/{filmId}
     */
    @DeleteMapping("/{userId}/{filmId}")
    public ResponseEntity<String> removeFromWatched(@PathVariable Long userId, @PathVariable Long filmId) {
        watchedFilmService.removeFromWatched(userId, filmId);
        return ResponseEntity.ok("Film watched list'ten kaldırıldı.");
    }
}

