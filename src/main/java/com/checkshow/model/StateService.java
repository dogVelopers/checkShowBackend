package com.checkshow.model;

import com.checkshow.entity.State;
import com.checkshow.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public State findById(Short id) {
        return stateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }
}
