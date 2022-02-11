package com.checkshow.dto.response;

import com.checkshow.entity.Facility;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FacilityResponse {

    private final String id;
    private final String facilityName;
    private final Short facilityCount;
    private final String facilityCharacteristic;
    private final Short yearOpen;
    private final Integer seatScale;
    private final String telNumber;
    private final String relateUrl;
    private final String address;
    private final double latitude;
    private final double longitude;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public FacilityResponse(Facility entity) {
        this.id = entity.getId();
        this.facilityName = entity.getFacilityName();
        this.facilityCount = entity.getFacilityCount();
        this.facilityCharacteristic = entity.getFacilityCharacteristic();
        this.yearOpen = entity.getYearOpen();
        this.seatScale = entity.getSeatScale();
        this.telNumber = entity.getTelNumber();
        this.relateUrl = entity.getRelateUrl();
        this.address = entity.getAddress();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
