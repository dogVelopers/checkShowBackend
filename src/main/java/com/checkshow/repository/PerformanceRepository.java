package com.checkshow.repository;

import com.checkshow.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PerformanceRepository extends JpaRepository<Performance, String>, QuerydslPredicateExecutor<Performance> {

}
