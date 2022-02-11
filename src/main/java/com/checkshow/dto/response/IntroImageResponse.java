package com.checkshow.dto.response;

import com.checkshow.entity.IntroImage;
import lombok.Getter;

@Getter
public class IntroImageResponse {

    private final String url;

    public IntroImageResponse(IntroImage entity) {
        this.url = entity.getUrl();
    }
}
