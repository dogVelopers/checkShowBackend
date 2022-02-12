package com.checkshow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    NOT_NULL_PARAM(HttpStatus.BAD_REQUEST, "해당 요청에서 Param은 값이 필수입니다.(not null)"),
    CANNOT_FORMAT_NUMBER(HttpStatus.BAD_REQUEST, "Param으로 보낸 값이 정수형으로 변환할 수 없습니다."),

    /* 404 NOT_FOUND : Response 찾을 수 없음. */
    FACILITY_NOT_FOUND(HttpStatus.NOT_FOUND, "id로 Facility를 찾을 수 없습니다."),
    PERFORMANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "id로 Performance를 찾을 수 없습니다."),
    RANKING_NOT_FOUND(HttpStatus.NOT_FOUND, "id로 Ranking을 찾을 수 없습니다."),
    GENRE_NOT_FOUND(HttpStatus.NOT_FOUND, "id로 Genre를 찾을 수 없습니다.(genreId 확인 요망)"),
    GU_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "id로 GuCode를 찾을 수 없습니다.(guCode 확인 요망)"),
    STATE_NOT_FOUND(HttpStatus.NOT_FOUND, "id로 State를 찾을 수 없습니다.(stateId 확인 요망)"),


    ;

    private final HttpStatus httpStatus;
    private final String comment;
}
