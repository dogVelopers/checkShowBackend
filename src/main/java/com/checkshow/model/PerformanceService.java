package com.checkshow.model;

import com.checkshow.dto.request.PerformanceRequest;
import com.checkshow.dto.response.PerformanceResponse;
import com.checkshow.entity.Genre;
import com.checkshow.entity.Performance;
import com.checkshow.entity.State;
import com.checkshow.exception.CustomException;
import com.checkshow.exception.ErrorCode;
import com.checkshow.predicate.PerformancePredicate;
import com.checkshow.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Transactional
    public String save(final PerformanceRequest dto) {
        Performance entity = performanceRepository.save(dto.toEntity());
        return entity.getId();
    }

    public PerformanceResponse findById(final String id) {
        Performance entity = performanceRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PERFORMANCE_NOT_FOUND));
        return new PerformanceResponse(entity);
    }

    public Page<PerformanceResponse> search(final Pageable pageable, final Genre genre, final State state, final Byte age) {
        return performanceRepository.findAll(PerformancePredicate.search(genre, state, age), pageable).map(PerformanceResponse::new);
    }
}
