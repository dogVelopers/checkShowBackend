package com.checkshow.repository;

import com.checkshow.entity.Genre;
import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, String> {

    List<Performance> findAllByGenre_Comment(String comment);

    List<Performance> findAllByGenre_DetailComment(String detailComment);

    List<Performance> findAllByAgeLessThan(Byte age);
}
