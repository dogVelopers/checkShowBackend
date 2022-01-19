package com.checkshow.dto.request;

import com.checkshow.entity.Genre;
import com.checkshow.entity.Performance;

public class RankingRequest {

    private Long id;
    private Performance performance;
    private Genre genre;
    private Byte rankNumber;
}
