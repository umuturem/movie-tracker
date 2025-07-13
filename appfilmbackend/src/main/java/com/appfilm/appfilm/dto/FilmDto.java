package com.appfilm.appfilm.dto;

/**
 * FilmDto:
 *  - Frontend'e döneceğimiz sade film verisini temsil eder
 *  - Entity içindeki tüm alanları içermez (gerekli olanlar alınır)
 */
public class FilmDto {

    private Long id;
    private String title;
    private String description;
    private String imageName;

    public FilmDto() {}

    public FilmDto(Long id, String title, String description, String imageName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageName = imageName;
    }

    // Getter & Setter'lar

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
