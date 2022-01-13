package com.checkshow.dto.request;

import com.checkshow.entity.Facility;
import lombok.Getter;

@Getter
public class FacilityRequest {

    private String id;
    private String facilityName;
    private String facilityCount;
    private String facilityCharacteristic;
    private Short yearOpen;
    private Short seatScale;
    private String telNumber;
    private String relateUrl;
    private String address;
    private double latitude;
    private double longitude;

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
