package com.checkshow.dto.response;

import com.checkshow.entity.Ranking;
import com.checkshow.entity.constant.GuCodeEnum;
import com.checkshow.model.IntroImageService;
import lombok.Getter;

import java.util.List;

@Getter
public class RankingResponse extends PerformanceResponse {

    private final String genreComment;
    private final String guName;
    private final Byte rankNumber;
    private final List<IntroImageResponse> introImages;

    public RankingResponse(Ranking entity, IntroImageService introImageService) {
        super(entity.getPerformance());
        this.genreComment = entity.getGenre().toEnum().getComment();
        this.guName = GuCodeEnum.findByGuCode(entity.getGuCode()).getGuName();
        this.rankNumber = entity.getRankNumber();
        this.introImages = introImageService.findAllByPerformanceId(getId());
    }
}
