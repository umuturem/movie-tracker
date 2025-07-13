package com.appfilm.appfilm.service.impl;

import com.appfilm.appfilm.dto.WatchListRequestDto;
import com.appfilm.appfilm.dto.WatchListResponseDto;
import com.appfilm.appfilm.entity.Film;
import com.appfilm.appfilm.entity.User;
import com.appfilm.appfilm.entity.WatchListFilm;
import com.appfilm.appfilm.repository.FilmRepository;
import com.appfilm.appfilm.repository.UserRepository;
import com.appfilm.appfilm.repository.WatchListFilmRepository;
import com.appfilm.appfilm.service.WatchListFilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListFilmServiceImpl implements WatchListFilmService {

    private final WatchListFilmRepository watchListFilmRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public WatchListFilmServiceImpl(WatchListFilmRepository watchListFilmRepository,
                                    UserRepository userRepository,
                                    FilmRepository filmRepository) {
        this.watchListFilmRepository = watchListFilmRepository;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    /**
     * Kullanıcının watchlist'ine film ekler.
     * Aynı film tekrar eklenmeye çalışılırsa hata vermez (kontrol yapılır).
     */
    @Override
    public void addToWatchList(WatchListRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Long filmId = requestDto.getFilmId();

        // Eğer kullanıcı daha önce bu filmi eklediyse, tekrar ekleme!
        if (watchListFilmRepository.existsByUserIdAndFilmId(userId, filmId)) {
            return; // zaten var → işlem yapma
        }

        // User ve Film objelerini veritabanından al
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        // WatchListFilm nesnesi oluştur ve kaydet
        WatchListFilm watchListFilm = new WatchListFilm(user, film);
        watchListFilmRepository.save(watchListFilm);
    }

    /**
     * Kullanıcının watchlist'indeki tüm filmleri DTO formatında döner.
     */
    @Override
    public List<WatchListResponseDto> getWatchListFilmsByUser(Long userId) {
        List<WatchListFilm> watchListFilms = watchListFilmRepository.findByUserId(userId);

        return watchListFilms.stream()
                .map(wf -> {
                    Film film = wf.getFilm();
                    return new WatchListResponseDto(
                            film.getId(),
                            film.getTitle(),
                            film.getDescription(),
                            film.getImageName()
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Kullanıcının watchlist'inden bir filmi çıkarır.
     */
    @Override
    @Transactional
    public void removeFromWatchList(Long userId, Long filmId) {
        watchListFilmRepository.deleteByUserIdAndFilmId(userId, filmId);
    }
} 