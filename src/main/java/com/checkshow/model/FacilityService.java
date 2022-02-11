package com.checkshow.model;

import com.checkshow.dto.request.FacilityRequest;
import com.checkshow.dto.response.FacilityResponse;
import com.checkshow.entity.Facility;
import com.checkshow.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<FacilityResponse> findAll(Pageable pageable) {
        Page<Facility> page = facilityRepository.findAll(pageable);

        return page.map(FacilityResponse::new);
    }

    public FacilityResponse findById(final String id) {
        Facility entity = facilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("404"));
        return new FacilityResponse(entity);
    }
}
