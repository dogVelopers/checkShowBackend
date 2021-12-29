package com.checkshow.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Facility Entity
 * id : PK(String)
 * facilityName : 공연 시설명
 * facilityCount : 공연 시설 수
 * facilityCharacteristic : 시설 특성
 * yearOpen : 개관연도
 * seatScale : 좌석수
 * telNumber : 전화번호
 * relateUrl : 홈페이지 주소
 * address : 주소
 * latitude : 위도
 * longitude : 경도
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Facility {

    @Id
    private String id;

    @Column(nullable = false)
    private String facilityName;

    @Column(nullable = false)
    private String facilityCount;

    @Column
    private String facilityCharacteristic;

    @Column
    private Short yearOpen;

    @Column
    private Short seatScale;

    @Column
    private String telNumber;

    @Column
    private String relateUrl;

    @Column
    private String address;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @CreatedDate
    private java.util.Date createdDate;

    @LastModifiedDate
    private java.util.Date modifiedDate;

    @Builder
    public Facility(String id, String facilityName, String facilityCount, String facilityCharacteristic, Short yearOpen, Short seatScale, String telNumber, String relateUrl, String address, double latitude, double longitude) {
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
}
