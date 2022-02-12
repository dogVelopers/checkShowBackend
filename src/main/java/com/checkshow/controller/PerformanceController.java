package com.checkshow.controller;

import com.checkshow.dto.response.IntroImageResponse;
import com.checkshow.dto.response.PerformanceResponse;
import com.checkshow.entity.Genre;
import com.checkshow.entity.State;
import com.checkshow.model.GenreService;
import com.checkshow.model.IntroImageService;
import com.checkshow.model.PerformanceService;
import com.checkshow.model.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    private final IntroImageService introImageService;

    private final GenreService genreService;

    private final StateService stateService;

    @GetMapping("/performances/{id}")
    public ResponseEntity<PerformanceResponse> findById(@PathVariable final String id) {
        PerformanceResponse performanceResponse = performanceService.findById(id);

        return new ResponseEntity<>(performanceResponse, HttpStatus.OK);
    }

    @GetMapping("/performances/{id}/images")
    public ResponseEntity<List<IntroImageResponse>> findIntroImagesByPerformanceId(@PathVariable final String id) {
        List<IntroImageResponse> introImageResponses = introImageService.findAllByPerformanceId(id);

        return new ResponseEntity<>(introImageResponses, HttpStatus.OK);
    }

    @GetMapping("/performances")
    public ResponseEntity<Page<PerformanceResponse>> findAll(Pageable pageable,
                                             final Short genreId,
                                             final Short stateId,
                                             final Byte age) {
        Genre genre = null;
        State state = null;

        if (genreId != null) {
            genre = genreService.findById(genreId);
        }

        if (stateId != null) {
            state = stateService.findById(stateId);
        }

        Page<PerformanceResponse> performanceResponses = performanceService.search(pageable, genre, state, age);

        if (performanceResponses.isEmpty()) {
            return new ResponseEntity<>(performanceResponses, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(performanceResponses, HttpStatus.OK);
    }

}
