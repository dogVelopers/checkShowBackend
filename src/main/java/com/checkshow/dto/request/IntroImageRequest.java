package com.checkshow.dto.request;

import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;
import lombok.Builder;

public class IntroImageRequest {

    private Performance performance;
    private String url;

    @Builder
    public IntroImageRequest(Performance performance, String url) {
        this.performance = performance;
        this.url = url;
    }

    public IntroImage toEntity() {
        return IntroImage.builder()
                .performance(performance)
                .url(url)
                .build();
    }
}
