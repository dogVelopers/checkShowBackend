package com.checkshow.controller;

import com.checkshow.dto.response.FacilityResponse;
import com.checkshow.model.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/facilities/{id}")
    public ResponseEntity<FacilityResponse> findById(@PathVariable final String id) {
        FacilityResponse facilityResponse = facilityService.findById(id);

        return new ResponseEntity<>(facilityResponse, HttpStatus.OK);
    }

    @GetMapping("/facilities")
    public ResponseEntity<Page<FacilityResponse>> findByAll(Pageable pageable) {
        Page<FacilityResponse> page = facilityService.findAll(pageable);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
