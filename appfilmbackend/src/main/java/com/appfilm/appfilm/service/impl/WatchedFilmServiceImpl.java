package com.appfilm.appfilm.service.impl;

import com.appfilm.appfilm.dto.WatchedRequestDto;
import com.appfilm.appfilm.dto.WatchedResponseDto;
import com.appfilm.appfilm.entity.Film;
import com.appfilm.appfilm.entity.User;
import com.appfilm.appfilm.entity.WatchedFilm;
import com.appfilm.appfilm.repository.FilmRepository;
import com.appfilm.appfilm.repository.UserRepository;
import com.appfilm.appfilm.repository.WatchedFilmRepository;
import com.appfilm.appfilm.service.WatchedFilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchedFilmServiceImpl implements WatchedFilmService {

    private final WatchedFilmRepository watchedFilmRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public WatchedFilmServiceImpl(WatchedFilmRepository watchedFilmRepository,
                                  UserRepository userRepository,
                                  FilmRepository filmRepository) {
        this.watchedFilmRepository = watchedFilmRepository;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    /**
     * Kullanıcının izlediği film listesini veritabanına kaydeder.
     * Aynı film tekrar eklenmeye çalışılırsa hata vermez (kontrol yapılır).
     */
    @Override
    public void addToWatched(WatchedRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Long filmId = requestDto.getFilmId();

        // Eğer kullanıcı daha önce bu filmi eklediyse, tekrar ekleme!
        if (watchedFilmRepository.findByUserIdAndFilmId(userId, filmId).isPresent()) {
            return; // zaten var → işlem yapma
        }

        // User ve Film objelerini veritabanından al
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        // WatchedFilm nesnesi oluştur ve kaydet
        WatchedFilm watchedFilm = new WatchedFilm(user, film);
        watchedFilmRepository.save(watchedFilm);
    }

    /**
     * Kullanıcının izlediği tüm filmleri DTO formatında döner.
     */
    @Override
    public List<WatchedResponseDto> getWatchedFilmsByUser(Long userId) {
        List<WatchedFilm> watchedList = watchedFilmRepository.findByUserId(userId);

        return watchedList.stream()
                .map(wf -> {
                    Film film = wf.getFilm();
                    return new WatchedResponseDto(
                            film.getId(),
                            film.getTitle(),
                            film.getDescription(),
                            film.getImageName()
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Kullanıcının izlediği filmler listesinden bir filmi çıkarır.
     */
    @Override
    public void removeFromWatched(Long userId, Long filmId) {
        watchedFilmRepository.findByUserIdAndFilmId(userId, filmId)
                .ifPresent(watchedFilmRepository::delete);
    }
}

