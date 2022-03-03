package com.checkshow.dto.response;

import com.checkshow.entity.Ranking;
import com.checkshow.entity.constant.GuCodeEnum;
import lombok.Getter;

@Getter
public class RankingResponse {

    private final Long id;
    private final PerformanceResponse performance;
    private final String genreComment;
    private final String guName;
    private final Byte rankNumber;

    public RankingResponse(Ranking entity) {
        this.id = entity.getId();
        this.performance = new PerformanceResponse(entity.getPerformance());
        this.genreComment = entity.getGenre().toEnum().getComment();
        this.guName = GuCodeEnum.findByGuCode(entity.getGuCode()).getGuName();
        this.rankNumber = entity.getRankNumber();
    }
}
