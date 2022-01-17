package com.checkshow.model;

import com.checkshow.dto.request.FacilityRequest;
import com.checkshow.entity.Facility;
import com.checkshow.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;

    @Transactional
    public String save(final FacilityRequest dto) {
        Facility entity = facilityRepository.save(dto.toEntity());
        return entity.getId();
    }
}
