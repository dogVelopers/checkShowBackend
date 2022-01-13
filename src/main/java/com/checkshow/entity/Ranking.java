package com.checkshow.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Ranking Entity
 * id : PK
 * genreCode : 예매상황판용 장르코드(장르 엔티티랑 달라서 따로 뺌)
 * rankNumber : 순위
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Performance.class)
    @JoinColumn
    private Performance performance;

    private String genreCode;

    @Column(nullable = false)
    private Byte rankNumber;

    @Builder
    public Ranking(Long id, Performance performance, String genreCode, Byte rankNumber) {
        this.id = id;
        this.performance = performance;
        this.genreCode = genreCode;
        this.rankNumber = rankNumber;
    }
}
