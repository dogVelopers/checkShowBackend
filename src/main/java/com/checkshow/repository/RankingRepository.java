package com.checkshow.repository;

import com.checkshow.entity.Genre;
import com.checkshow.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findAllByGenreAndGuCodeOrderByRankNumber(Genre genre, String guCode);
}
