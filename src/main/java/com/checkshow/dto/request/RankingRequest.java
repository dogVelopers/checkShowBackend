package com.checkshow.dto.request;

import com.checkshow.entity.Genre;
import com.checkshow.entity.Performance;
import com.checkshow.entity.Ranking;
import lombok.Builder;

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

    @Builder
    public RankingRequest(Performance performance, Genre genre, String guCode, Byte rankNumber) {
        this.performance = performance;
        this.genre = genre;
        this.guCode = guCode;
        this.rankNumber = rankNumber;
    }
}
