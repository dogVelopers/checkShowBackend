package com.checkshow.model;

import com.checkshow.dto.request.IntroImageRequest;
import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;
import com.checkshow.repository.IntroImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IntroImageService {

    private final IntroImageRepository introImageRepository;

    @Transactional
    public void saveAll(final List<IntroImageRequest> list) {
        List<IntroImage> introImages = list.stream().map(IntroImageRequest::toEntity).collect(Collectors.toList());
        introImageRepository.saveAll(introImages);
    }

    @Transactional
    public void deleteAllByPerformance(final Performance performance) {
        introImageRepository.deleteAllByPerformance(performance);
    }
}
