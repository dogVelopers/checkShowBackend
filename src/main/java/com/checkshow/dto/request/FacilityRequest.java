package com.checkshow.dto.request;

import com.checkshow.entity.Facility;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FacilityRequest {

    private String id;
    private String facilityName;
    private Short facilityCount;
    private String facilityCharacteristic;
    private Short yearOpen;
    private Integer seatScale;
    private String telNumber;
    private String relateUrl;
    private String address;
    private double latitude;
    private double longitude;

    @Builder
    public FacilityRequest(String id, String facilityName, Short facilityCount, String facilityCharacteristic, Short yearOpen, Integer seatScale, String telNumber, String relateUrl, String address, double latitude, double longitude) {
        this.id = id;
        this.facilityName = facilityName;
        this.facilityCount = facilityCount;
        this.facilityCharacteristic = facilityCharacteristic;
        this.yearOpen = yearOpen;
        this.seatScale = seatScale;
        this.telNumber = telNumber;
        this.relateUrl = relateUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Facility toEntity() {
        return Facility.builder()
                .id(id)
                .facilityName(facilityName)
                .facilityCount(facilityCount)
                .facilityCharacteristic(facilityCharacteristic)
                .yearOpen(yearOpen)
                .seatScale(seatScale)
                .telNumber(telNumber)
                .relateUrl(relateUrl)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
