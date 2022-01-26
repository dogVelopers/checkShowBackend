package com.checkshow;

import com.checkshow.dto.request.FacilityRequest;
import com.checkshow.dto.request.RankingRequest;
import com.checkshow.entity.Genre;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.model.GenreService;
import com.checkshow.parsing.FacilityParsing;
import com.checkshow.parsing.PerformanceParsing;
import com.checkshow.parsing.RankingParsing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("dev")
public class ParsingTests {

    @Autowired
    private GenreService genreService;

    @Autowired
    private PerformanceParsing performanceParsing;

    @Autowired
    private FacilityParsing facilityParsing;

    @Autowired
    private RankingParsing rankingParsing;

    // api키가 제대로 불러와지는지 테스트
    @Test
    void printApiKey() {
        System.out.println(facilityParsing.getApiKey());
    }

    // performance id들을 불러와서 출력 테스트;
    @Test
    void parsingPerformanceIdsAndPrint() {
        List<String> list = performanceParsing.loadAllPerformanceIdToday();

        for (String value : list) {
            System.out.println(value);
        }
    }

    // performance를 파싱해서 안에 있는 데이터가 제대로 있는지 확인.
    @Test
    void parsingPerformanceDataById() {
        Map<String, Object> map = performanceParsing.loadDataByPerformanceId("PF185540");

//        for (String key : map.keySet()) {
//            System.out.printf("키: %s, 값: %s%n", key, map.get(key));
//        }
        Genre genre = (Genre) map.get("genre");
        System.out.println(genre.getDetailCode());
    }

    // 공연이 제대로 파싱되는지 확인
    @Test
    void parsingFacilityDataById() {
        FacilityRequest dto = facilityParsing.loadDataByFacilityId("FC001247");

        System.out.println(dto.getAddress());
    }

    // 랭킹이 제대로 파싱되는지 확이
    @Test
    void parsingRanking() {
        List<RankingRequest> rankingRequestList = rankingParsing.findRankingAllByGenreAndGuCode(GenreEnum.아동.toEntity(genreService), "11");
        System.out.println(rankingRequestList.size());
    }
}
