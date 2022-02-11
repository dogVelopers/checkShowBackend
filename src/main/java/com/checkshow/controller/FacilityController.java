package com.checkshow.controller;

import com.checkshow.dto.response.FacilityResponse;
import com.checkshow.model.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public FacilityResponse findById(@PathVariable final String id) {
        return facilityService.findById(id);
    }

    @GetMapping("/facilities")
    public Page<FacilityResponse> findByAll(Pageable pageable) {
        return facilityService.findAll(pageable);
    }
}
