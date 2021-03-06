package com.checkshow.controller;

import com.checkshow.dto.response.RankingResponse;
import com.checkshow.entity.Genre;
import com.checkshow.entity.constant.GuCodeEnum;
import com.checkshow.model.GenreService;
import com.checkshow.model.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    private final GenreService genreService;

    @GetMapping("/rankings")
    public ResponseEntity<List<RankingResponse>> findAllByGenreAndGuCodeOrderByRankNumber(final Short genreId, final String guCode) {
        Genre genre = genreService.findById(genreId);
        GuCodeEnum.findByGuCode(guCode);

        List<RankingResponse> rankingResponses = rankingService.findAllByGenreAndGuCodeOrderByRankNumber(genre, guCode);

        if (rankingResponses.isEmpty()) {
            return new ResponseEntity<>(rankingResponses, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rankingResponses, HttpStatus.OK);
    }
}
