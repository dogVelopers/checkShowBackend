package com.checkshow.model;

import com.checkshow.dto.request.PerformanceRequest;
import com.checkshow.entity.Performance;
import com.checkshow.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
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
}
