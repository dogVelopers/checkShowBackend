package com.checkshow.dto.request;

import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;
import lombok.Builder;

public class IntroImageRequest {

    private String id;
    private Performance performance;
    private String url;

    @Builder
    public IntroImageRequest(String id, Performance performance, String url) {
        this.id = id;
        this.performance = performance;
        this.url = url;
    }

    public IntroImage toEntity() {
        return IntroImage.builder()
                .id(id)
                .performance(performance)
                .url(url)
                .build();
    }
}
