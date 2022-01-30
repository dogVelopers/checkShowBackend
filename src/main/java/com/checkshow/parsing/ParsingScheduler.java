package com.checkshow.parsing;

import com.checkshow.dto.request.RankingRequest;
import com.checkshow.entity.Genre;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.entity.constant.GuCodeEnum;
import com.checkshow.model.GenreService;
import com.checkshow.model.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ParsingScheduler {

    private final RankingService rankingService;

    private final GenreService genreService;

    private final RankingParsing rankingParsing;

    // 매일 오전 3시마다 데이터 파싱해서 업데이트
    @Scheduled(cron = "0 0 3 * * *")
    public void autoParsingAndSave() {
        rankingService.deleteAll();
        List<Genre> genreList = Arrays.stream(GenreEnum.values()).map(x -> x.toEntity(genreService)).collect(Collectors.toList());
        List<String> guCodeList = Arrays.stream(GuCodeEnum.values()).map(GuCodeEnum::getGuCode).collect(Collectors.toList());

        for (Genre genre: genreList) {
            for (String guCode: guCodeList) {
                List<RankingRequest> list = rankingParsing.findRankingAllByGenreAndGuCode(genre, guCode);
                rankingService.saveAll(list);
            }
        }
    }

}
