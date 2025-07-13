package com.appfilm.appfilm.service.impl;

import com.appfilm.appfilm.dto.FilmDto;
import com.appfilm.appfilm.entity.Film;
import com.appfilm.appfilm.repository.FilmRepository;
import com.appfilm.appfilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * FilmServiceImpl:
 *  - FilmService arayüzünü (interface) uygular
 *  - Veritabanından sayfalı şekilde film çeker
 *  - Film entity'lerini FilmDto'ya dönüştürüp geri döner
 */
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<FilmDto> getAllFilms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Sayfa + boyut belirlenir
        List<Film> films = filmRepository.findAll(pageable).getContent(); // Sayfalı film listesi alınır

        // Her bir Film nesnesi FilmDto'ya dönüştürülür
        return films.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Entity → DTO dönüşümünü yapan yardımcı metod
    private FilmDto convertToDto(Film film) {
        return new FilmDto(
                film.getId(),
                film.getTitle(),
                film.getDescription(),
                film.getImageName()
        );
    }
}
