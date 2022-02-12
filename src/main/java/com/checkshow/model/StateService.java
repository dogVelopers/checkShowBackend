package com.checkshow.model;

import com.checkshow.entity.State;
import com.checkshow.exception.CustomException;
import com.checkshow.exception.ErrorCode;
import com.checkshow.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public State findById(Short id) {
        return stateRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.STATE_NOT_FOUND));
    }
}
