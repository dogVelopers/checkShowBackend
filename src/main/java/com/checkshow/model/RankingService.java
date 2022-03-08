package com.checkshow.model;

import com.checkshow.dto.request.RankingRequest;
import com.checkshow.dto.response.RankingResponse;
import com.checkshow.entity.Genre;
import com.checkshow.entity.Ranking;
import com.checkshow.exception.CustomException;
import com.checkshow.exception.ErrorCode;
import com.checkshow.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    private final IntroImageService introImageService;

    @Transactional
    public void saveAll(final List<RankingRequest> list) {
        List<Ranking> rankings = list.stream().map(RankingRequest::toEntity).collect(Collectors.toList());
        rankingRepository.saveAll(rankings);
    }

    @Transactional
    public void deleteAll() {
        rankingRepository.deleteAll();
    }

    public RankingResponse findById(final Long id) {
        Ranking entity = rankingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.RANKING_NOT_FOUND));
        return new RankingResponse(entity, introImageService);
    }

    public List<RankingResponse> findAllByGenreAndGuCodeOrderByRankNumber(final Genre genre, final String guCode) {
        List<Ranking> rankingList = rankingRepository.findAllByGenreAndGuCodeOrderByRankNumber(genre, guCode);

        return rankingList.stream().map(x -> new RankingResponse(x, introImageService)).collect(Collectors.toList());
    }
}
