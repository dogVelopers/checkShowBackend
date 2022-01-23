package com.checkshow.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Ranking Entity
 * id : PK
 * performance : 공연 매핑
 * genre : 장르 매핑
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

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn
    private Genre genre;

    @Column
    private String guCode;

    @Column(nullable = false)
    private Byte rankNumber;

    @Builder
    public Ranking(Long id, Performance performance, Genre genre, String guCode, Byte rankNumber) {
        this.id = id;
        this.performance = performance;
        this.genre = genre;
        this.guCode = guCode;
        this.rankNumber = rankNumber;
    }
}
