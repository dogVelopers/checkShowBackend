package com.checkshow.dto.request;

import com.checkshow.entity.Genre;
import com.checkshow.entity.Performance;
import com.checkshow.entity.Ranking;

public class RankingRequest {

    private Performance performance;
    private Genre genre;
    private String guCode;
    private Byte rankNumber;

    public Ranking toEntity() {
        return Ranking.builder()
                .performance(performance)
                .genre(genre)
                .guCode(guCode)
                .rankNumber(rankNumber)
                .build();
    }
}
