package com.appfilm.appfilm.repository;

import com.appfilm.appfilm.entity.WatchedFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchedFilmRepository extends JpaRepository<WatchedFilm, Long> {

    @Query("SELECT wf FROM WatchedFilm wf WHERE wf.user.id = :userId")
    List<WatchedFilm> findByUserId(@Param("userId") Long userId);

    @Query("SELECT wf FROM WatchedFilm wf WHERE wf.user.id = :userId AND wf.film.id = :filmId")
    Optional<WatchedFilm> findByUserIdAndFilmId(@Param("userId") Long userId, @Param("filmId") Long filmId);
}

