package com.checkshow.model;

import com.checkshow.dto.request.RankingRequest;
import com.checkshow.entity.Ranking;
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

    @Transactional
    public void saveAll(List<RankingRequest> list) {
        List<Ranking> rankings = list.stream().map(RankingRequest::toEntity).collect(Collectors.toList());
        rankingRepository.saveAll(rankings);
    }

    @Transactional
    public void deleteAll() {
        rankingRepository.deleteAll();
    }
}
