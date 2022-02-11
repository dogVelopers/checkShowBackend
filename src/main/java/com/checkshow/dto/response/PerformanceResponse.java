package com.checkshow.dto.response;

import com.checkshow.entity.Performance;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
public class PerformanceResponse {

    private final String id;
    private final String genreName;
    private final String state;
    private final String facility_id;
    private final String facilityDetailName;
    private final String performanceName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String cast;
    private final String crew;
    private final String runtime;
    private final Byte age;
    private final String productionCompany;
    private final String posterUrl;
    private final String story;
    private final Boolean openRun;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;


    public PerformanceResponse(Performance entity) {
        this.id = entity.getId();
        this.genreName = entity.getGenre().toEnum().getComment();
        this.state = entity.getState().toEnum().getComment();
        this.facility_id = entity.getFacility().getId();
        this.facilityDetailName = entity.getFacilityDetailName();
        this.performanceName = entity.getPerformanceName();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.cast = entity.getCast();
        this.crew = entity.getCrew();
        this.runtime = entity.getRuntime();
        this.age = entity.getAge();
        this.productionCompany = entity.getProductionCompany();
        this.posterUrl = entity.getPosterUrl();
        this.story = entity.getStory();
        this.openRun = entity.getOpenRun();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
