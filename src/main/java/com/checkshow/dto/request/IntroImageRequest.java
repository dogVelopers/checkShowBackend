package com.checkshow.dto.request;

import com.checkshow.entity.IntroImage;
import com.checkshow.entity.Performance;

public class IntroImageRequest {

    private Performance performance;
    private String url;

    public IntroImage toEntity() {
        return IntroImage.builder()
                .performance(performance)
                .url(url)
                .build();
    }
}
