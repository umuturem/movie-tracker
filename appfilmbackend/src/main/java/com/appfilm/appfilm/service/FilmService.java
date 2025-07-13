package com.appfilm.appfilm.service;

import com.appfilm.appfilm.dto.FilmDto;
import java.util.List;

public interface FilmService {

    /**
     * Belirli bir sayfayı döner (pagination ile)
     *
     * @param page Sayfa numarası (0'dan başlar)
     * @param size Sayfa başına kaç film gösterilecek
     * @return FilmDto listesini döner
     */
    List<FilmDto> getAllFilms(int page, int size);
}

