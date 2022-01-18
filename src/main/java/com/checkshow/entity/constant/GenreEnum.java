package com.checkshow.entity.constant;

import com.checkshow.entity.Genre;
import com.checkshow.model.GenreService;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GenreEnum {
    연극(1, "AA", "AA", "연극"),
    뮤지컬(2, "AA", "AB", "뮤지컬"),
    무용(3, "BB", "BA", "무용"),
    클래식(4, "CC", "CA", "클래식"),
    오페라(5, "CC", "CB", "오페라"),
    국악(6, "CC", "CC", "국악"),
    복합(7, "EE", "EA", "복합");

    private final int id;
    private final String code;
    private final String detailCode;
    private final String comment;

    GenreEnum(final int id,
              final String code,
              final String detailCode,
              final String comment) {

        this.id = id;
        this.code = code;
        this.detailCode = detailCode;
        this.comment = comment;
    }

    public static GenreEnum findByComment(String input) {
        return Arrays.stream(GenreEnum.values())
                .filter(genreEnum -> genreEnum.comment.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("comment로 데이터를 찾을 수 없습니다." + input));
    }

    public static GenreEnum findById(int input) {
        return Arrays.stream(GenreEnum.values())
                .filter(genreEnum -> genreEnum.id == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("id로 데이터를 찾을 수 없습니다."));
    }

    public static GenreEnum findByCode(String input) {
        return Arrays.stream(GenreEnum.values())
                .filter(genreEnum -> (genreEnum.code + genreEnum.detailCode).equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("code로 데이터를 찾을 수 없습니다."));
    }

    public Genre toEntity(GenreService genreService) {
        return genreService.findById((short) getId());
    }
}
