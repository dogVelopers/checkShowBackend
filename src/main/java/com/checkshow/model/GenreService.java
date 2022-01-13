package com.checkshow.model;

import com.checkshow.entity.Genre;
import com.checkshow.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre findById(final Short id) {
        return genreRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }
}
