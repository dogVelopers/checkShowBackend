package com.checkshow;

import com.checkshow.dto.request.FacilityRequest;
import com.checkshow.dto.request.IntroImageRequest;
import com.checkshow.dto.request.PerformanceRequest;
import com.checkshow.dto.request.RankingRequest;
import com.checkshow.entity.Facility;
import com.checkshow.entity.Genre;
import com.checkshow.entity.Performance;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.entity.constant.GuCodeEnum;
import com.checkshow.entity.constant.StateEnum;
import com.checkshow.model.*;
import com.checkshow.parsing.FacilityParsing;
import com.checkshow.parsing.PerformanceParsing;
import com.checkshow.parsing.RankingParsing;
import com.checkshow.repository.FacilityRepository;
import com.checkshow.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("dev")
public class ServiceTests {

    @Autowired
    private GenreService genreService;

    @Autowired
    private StateService stateService;

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private IntroImageService introImageService;

    @Autowired
    private RankingService rankingService;

    @Autowired
    private FacilityParsing facilityParsing;

    @Autowired
    private PerformanceParsing performanceParsing;

    @Autowired
    private RankingParsing rankingParsing;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    // enum으로 장르 엔티티 조회
    @Test
    void toGenreEntity() {
        GenreEnum genreEnum = GenreEnum.findByComment("연극");
        System.out.println(genreEnum.toEntity(genreService).getDetailCode());
    }

    // enum으로 State 엔티티 조회
    @Test
    void toStateEntity() {
        StateEnum stateEnum = StateEnum.findByComment("공연중");
        System.out.println(stateEnum.toEntity(stateService).getComment());
    }

    // performance 저장 테스트
    @Test
    void savePerformance() {
        PerformanceRequest performanceRequest = PerformanceRequest.builder()
                .id("abcd")
                .performanceName("테스트")
                .state(StateEnum.공연예정.toEntity(stateService))
                .genre(GenreEnum.국악.toEntity(genreService))
                .build();

        System.out.println(performanceService.save(performanceRequest));
    }


    // introImages 리스트로 된 것들 저장 테스트
    @Test
    void saveIntroImages() {
        PerformanceRequest performanceRequest = PerformanceRequest.builder()
                .id("bc")
                .performanceName("테스트")
                .state(StateEnum.공연예정.toEntity(stateService))
                .genre(GenreEnum.국악.toEntity(genreService))
                .build();

        List<IntroImageRequest> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            IntroImageRequest introImageRequest = IntroImageRequest.builder()
                    .id(String.format("%s_%d", "테스트", (i + 1)))
                    .performance(performanceRequest.toEntity())
                    .url(String.valueOf(i))
                    .build();
            list.add(introImageRequest);
        }

        performanceService.save(performanceRequest);
        introImageService.saveAll(list);
    }

    // facility 데이터 불러와서 저장테스트
    @Test
    void saveFacilityParsing() {
        FacilityRequest facilityRequest = facilityParsing.loadDataByFacilityId("FC001247");
        System.out.println(facilityService.save(facilityRequest));
    }

    // 저장안된 엔티티가 매핑되는지 확인하고 저장이 잘되는지 확인
    @Test
    void entityMappingTest() {
        Facility facility = Facility.builder()
                .id("abcd")
                .facilityName("공연장 테스트")
                .facilityCount((short) 5)
                .build();

        Performance performance = Performance.builder()
                .id("Facility가 저장 안된 상태에서 매핑되는지 확인")
                .performanceName("asfd")
                .facility(facility)
                .build();

        facilityRepository.save(facility);

        performanceRepository.save(performance);

        System.out.println(performance.getFacility().getFacilityName());

    }

    // 공연 id 한 개를 파싱한 데이터를 저장.
    @Test
    void parsingPerformanceAndSave() {
        System.out.println(performanceParsing.saveDataByPerformanceId("PF132236"));
    }

    // 오늘 날짜 공연 id 여러개를 파싱해서 모두 저장 테스트
    @Test
    void parsingPerformanceAndAllSave() {
        List<String> performanceIdList = performanceParsing.loadAllPerformanceIdToday();
        performanceIdList.forEach(x -> performanceParsing.saveDataByPerformanceId(x));
    }

    // 어제 날짜 랭킹 리스트가 제대로 파싱되는지 확인.
    @Test
    void parsingRankingAndSaveAll() {
        List<RankingRequest> rankingRequestList = rankingParsing.findRankingAllByGenreAndGuCode(GenreEnum.연극.toEntity(genreService), "11");
        rankingService.saveAll(rankingRequestList);
    }

    // 모든 시 코드, Genre코드로 ranking을 불러와서 저장 테스트.
    // 20220123 기준 걸린시간 2분 22초, 1840개 데이터(239(시설)+478(이미지)+519(공연)+604(랭킹))
    @Test
    void saveAllByGuCodeAndGenre() {
        rankingService.deleteAll();
        List<Genre> genreList = Arrays.stream(GenreEnum.values()).map(x -> x.toEntity(genreService)).collect(Collectors.toList());
        List<String> guCodeList = Arrays.stream(GuCodeEnum.values()).map(GuCodeEnum::getGuCode).collect(Collectors.toList());

        int size = 0;
        for (Genre genre: genreList) {
            for (String guCode: guCodeList) {
                List<RankingRequest> list = rankingParsing.findRankingAllByGenreAndGuCode(genre, guCode);
                rankingService.saveAll(list);
                size += list.size();
            }
        }

        System.out.println(size);
    }
}
