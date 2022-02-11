package com.checkshow.controller;

import com.checkshow.dto.response.PerformanceResponse;
import com.checkshow.entity.Genre;
import com.checkshow.entity.State;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.entity.constant.StateEnum;
import com.checkshow.model.GenreService;
import com.checkshow.model.PerformanceService;
import com.checkshow.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    private final GenreService genreService;

    private final StateService stateService;

    @GetMapping("/performances/{id}")
    public PerformanceResponse findById(@PathVariable final String id) {
        return performanceService.findById(id);
    }

    @GetMapping("/performances")
    public Page<PerformanceResponse> findAll(Pageable pageable,
                                             Integer genreId,
                                             Integer stateId,
                                             Byte age) {
        Genre genre = null;
        State state = null;

        if (genreId != null) {
            genre = GenreEnum.findById(genreId).toEntity(genreService);
        }

        if (stateId != null) {
            state = StateEnum.findById(stateId).toEntity(stateService);
        }

        return performanceService.search(pageable, genre, state, age);
    }

}
