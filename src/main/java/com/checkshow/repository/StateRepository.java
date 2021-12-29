package com.checkshow.repository;

import com.checkshow.entity.Facility;
import com.checkshow.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Short> {
}
