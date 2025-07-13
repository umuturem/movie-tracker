package com.appfilm.appfilm.controller;

import com.appfilm.appfilm.dto.FilmDto;
import com.appfilm.appfilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FilmController:
 *  - /api/films endpoint’ini sunar
 *  - Sayfa numarası ve boyutu query parametre olarak alır
 *  - Gerekli film listesini FilmDto şeklinde döner
 */
@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    /**
     * Tüm filmleri sayfalı şekilde döner
     * Örnek: GET /api/films?page=0&size=10
     */
    @GetMapping
    public List<FilmDto> getAllFilms(@RequestParam int page, @RequestParam int size) {
        return filmService.getAllFilms(page, size);
    }
}

