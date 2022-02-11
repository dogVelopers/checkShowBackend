package com.checkshow.dto.response;

import com.checkshow.entity.Ranking;
import com.checkshow.entity.constant.GuCodeEnum;
import lombok.Getter;

@Getter
public class RankingResponse {

    private final Long id;
    private final String performanceId;
    private final String genreComment;
    private final String guName;
    private final Byte rankNumber;

    public RankingResponse(Ranking entity) {
        this.id = entity.getId();
        this.performanceId = entity.getPerformance().getId();
        this.genreComment = entity.getGenre().toEnum().getComment();
        this.guName = GuCodeEnum.findByGuCode(entity.getGuCode()).getGuName();
        this.rankNumber = entity.getRankNumber();
    }
}
