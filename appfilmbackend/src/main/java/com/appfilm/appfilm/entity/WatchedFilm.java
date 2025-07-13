package com.appfilm.appfilm.entity;

import jakarta.persistence.*;

@Entity
@Table(
  name = "watched_films",
  uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "film_id"})
)
public class WatchedFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    public WatchedFilm() {}

    public WatchedFilm(User user, Film film) {
        this.user = user;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}

