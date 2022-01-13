package com.checkshow.repository;

import com.checkshow.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, String> {

    List<Performance> findAllByGenre_Code(String code);

    List<Performance> findAllByGenre_DetailCode(String detailCode);

    List<Performance> findAllByAgeLessThan(Byte age);
}
