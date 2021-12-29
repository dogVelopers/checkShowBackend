package com.checkshow.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * IntroImage Entity
 * id: PK
 * performance : 공연 엔티티
 * url: image url
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IntroImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Performance.class)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Column(nullable = false)
    private String url;

    @Builder
    public IntroImage(Long id, Performance performance, String url) {
        this.id = id;
        this.performance = performance;
        this.url = url;
    }
}
