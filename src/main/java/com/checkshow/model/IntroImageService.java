package com.checkshow.model;

import com.checkshow.dto.request.IntroImageRequest;
import com.checkshow.dto.response.IntroImageResponse;
import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;
import com.checkshow.repository.IntroImageRepository;
import com.checkshow.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IntroImageService {

    private final IntroImageRepository introImageRepository;

    private final PerformanceRepository performanceRepository;

    @Transactional
    public void saveAll(final List<IntroImageRequest> list) {
        List<IntroImage> introImages = list.stream().map(IntroImageRequest::toEntity).collect(Collectors.toList());
        introImageRepository.saveAll(introImages);
    }

    @Transactional
    public void deleteAllByPerformance(final Performance performance) {
        introImageRepository.deleteAllByPerformance(performance);
    }

    public List<IntroImageResponse> findAllByPerformanceId(final String id) {
        Performance performance = performanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("404"));
        List<IntroImage> list = introImageRepository.findAllByPerformance(performance);

        return list.stream().map(IntroImageResponse::new).collect(Collectors.toList());
    }
}
