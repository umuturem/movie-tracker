package com.appfilm.appfilm.dto;

/**
 * Bu sınıf, kullanıcıya gösterilecek "izlediği filmler" listesini sade bir şekilde taşır.
 * Film entity'si yerine sadece gerekli bilgiler verilir: id, başlık, açıklama, resim adı.
 */
public class WatchedResponseDto {

    private Long filmId;
    private String title;
    private String description;
    private String imageName;

    // Boş constructor – Spring için gerekir
    public WatchedResponseDto() {}

    // Parametreli constructor – veriyi kolayca set etmek için
    public WatchedResponseDto(Long filmId, String title, String description, String imageName) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.imageName = imageName;
    }

    // Getter metodları – frontend'in erişebilmesi için gereklidir
    public Long getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }
}

