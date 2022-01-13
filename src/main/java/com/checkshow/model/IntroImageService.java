package com.checkshow.model;

import com.checkshow.dto.request.IntroImageRequest;
import com.checkshow.entity.IntroImage;
import com.checkshow.repository.IntroImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntroImageService {

    private final IntroImageRepository introImageRepository;

    public Long save(final IntroImageRequest dto) {
        IntroImage entity = introImageRepository.save(dto.toEntity());
        return entity.getId();
    }
}
