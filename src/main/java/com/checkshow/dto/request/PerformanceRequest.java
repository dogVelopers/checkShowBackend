package com.checkshow.dto.request;

import com.checkshow.entity.*;
import lombok.Builder;

import java.time.LocalDate;

public class PerformanceRequest {

    private String id;
    private Genre genre;
    private State state;
    private Facility facility;
    private String facilityDetailName;
    private String performanceName;
    private String performanceTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private String cast;
    private String crew;
    private String runtime;
    private Byte age;
    private String productionCompany;
    private String price;
    private String posterUrl;
    private String story;
    private Boolean openRun;


    @Builder
    public PerformanceRequest(String id, Genre genre, State state, Facility facility, String facilityDetailName, String performanceName, String performanceTime, LocalDate startDate, LocalDate endDate, String cast, String crew, String runtime, Byte age, String productionCompany, String price, String posterUrl, String story, Boolean openRun) {
        this.id = id;
        this.genre = genre;
        this.state = state;
        this.facility = facility;
        this.facilityDetailName = facilityDetailName;
        this.performanceName = performanceName;
        this.performanceTime = performanceTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cast = cast;
        this.crew = crew;
        this.runtime = runtime;
        this.age = age;
        this.productionCompany = productionCompany;
        this.price = price;
        this.posterUrl = posterUrl;
        this.story = story;
        this.openRun = openRun;
    }

    public Performance toEntity() {

        return Performance.builder()
                .id(id)
                .genre(genre)
                .state(state)
                .facility(facility)
                .facilityDetailName(facilityDetailName)
                .performanceName(performanceName)
                .performanceTime(performanceTime)
                .startDate(startDate)
                .endDate(endDate)
                .cast(cast)
                .crew(crew)
                .runtime(runtime)
                .age(age)
                .productionCompany(productionCompany)
                .price(price)
                .posterUrl(posterUrl)
                .story(story)
                .openRun(openRun)
                .build();
    }
}
