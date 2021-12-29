package com.checkshow.repository;

import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntroImageRepository extends JpaRepository<IntroImage, Long> {

    List<IntroImage> findAllByPerformance(Performance performance);
}
