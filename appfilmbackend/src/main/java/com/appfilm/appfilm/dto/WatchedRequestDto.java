package com.appfilm.appfilm.dto;

/**
 * Bu sınıf, kullanıcıdan gelen "watched" isteği verisini taşımak için kullanılır.
 * Sadece userId ve filmId bilgisi yeterlidir.
 */
public class WatchedRequestDto {

    private Long userId;
    private Long filmId;

    // Boş constructor – Spring tarafından kullanılabilmesi için gerekir
    public WatchedRequestDto() {}

    // Parametreli constructor – elle nesne oluşturmak için
    public WatchedRequestDto(Long userId, Long filmId) {
        this.userId = userId;
        this.filmId = filmId;
    }

    // Getter ve Setter metodları – veriye erişmek ve değiştirmek için kullanılır
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }
}

