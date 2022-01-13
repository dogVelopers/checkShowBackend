package com.checkshow;

import com.checkshow.dto.request.PerformanceRequest;
import com.checkshow.entity.Genre;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.entity.constant.StateEnum;
import com.checkshow.model.GenreService;
import com.checkshow.model.PerformanceService;
import com.checkshow.model.StateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class ServiceTests {

    @Autowired
    private GenreService genreService;

    @Autowired
    private StateService stateService;

    @Autowired
    private PerformanceService performanceService;

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
}
