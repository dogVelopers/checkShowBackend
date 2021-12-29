package com.checkshow.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Performance Entity
 * id : PK(String)
 * genre : 장르 엔티티
 * state : State 엔티티
 * facility : 시설 엔티티
 * facilityDetailName : 시설 자세한명
 * performanceName : 공연명
 * startDate : 공연 시작일
 * endDate : 공연 종료일
 * cast : 출연진
 * crew : 제작진
 * runtime : 상영 시간
 * age : 관람연령
 * productionCompany : 제작사
 * posterUrl : 포스터 이미지 url
 * story : 줄거리
 * openRun : 오픈런 여부
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance {

    @Id
    private String id;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn
    private Genre genre;

    @ManyToOne(targetEntity = State.class)
    @JoinColumn
    private State state;

    @ManyToOne(targetEntity = Facility.class)
    @JoinColumn
    private Facility facility;

    @Column
    private String facilityDetailName;

    @Column(nullable = false)
    private String performanceName;

    @Column
    private java.util.Date startDate;

    @Column
    private java.util.Date endDate;

    @Column
    private String cast;

    @Column
    private String crew;

    @Column
    private String runtime;

    @Column
    private Byte age;

    @Column
    private String productionCompany;

    @Column
    private String posterUrl;

    @Column
    private String story;

    @Column(nullable = false, columnDefinition = "tinyint", length = 1)
    private Boolean openRun;

    @CreatedDate
    private java.util.Date createdDate;

    @LastModifiedDate
    private java.util.Date modifiedDate;

    @Builder
    public Performance(String id, Genre genre, State state, Facility facility, String facilityDetailName, String performanceName, Date startDate, Date endDate, String cast, String crew, String runtime, Byte age, String productionCompany, String posterUrl, String story, Boolean openRun) {
        this.id = id;
        this.genre = genre;
        this.state = state;
        this.facility = facility;
        this.facilityDetailName = facilityDetailName;
        this.performanceName = performanceName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cast = cast;
        this.crew = crew;
        this.runtime = runtime;
        this.age = age;
        this.productionCompany = productionCompany;
        this.posterUrl = posterUrl;
        this.story = story;
        this.openRun = openRun;
    }
}
