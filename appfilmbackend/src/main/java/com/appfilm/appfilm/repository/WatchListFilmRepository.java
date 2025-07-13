package com.appfilm.appfilm.repository;

import com.appfilm.appfilm.entity.WatchListFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListFilmRepository extends JpaRepository<WatchListFilm, Long> {

    @Query("SELECT wf FROM WatchListFilm wf WHERE wf.user.id = :userId")
    List<WatchListFilm> findByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(wf) > 0 FROM WatchListFilm wf WHERE wf.user.id = :userId AND wf.film.id = :filmId")
    boolean existsByUserIdAndFilmId(@Param("userId") Long userId, @Param("filmId") Long filmId);

    void deleteByUserIdAndFilmId(Long userId, Long filmId);
} 