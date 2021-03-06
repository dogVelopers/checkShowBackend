package com.checkshow.repository;

import com.checkshow.entity.Genre;
import com.checkshow.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findAllByGenreAndGuCodeOrderByRankNumber(Genre genre, String guCode);

    @Override
    @Modifying
    @Query(
            value = "truncate table ranking",
            nativeQuery = true
    )
    void deleteAll();
}
