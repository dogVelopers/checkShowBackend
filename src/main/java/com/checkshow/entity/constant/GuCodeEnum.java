package com.checkshow.entity.constant;

import com.checkshow.exception.CustomException;
import com.checkshow.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GuCodeEnum {
    서울특별시("11", "서울특별시"),
    부산광역시("26", "부산광역시"),
    대구광역시("27", "대구광역시"),
    인천광역시("28", "인천광역시"),
    광주광역시("29", "광주광역시"),
    대전광역시("30", "대전광역시"),
    울산광역시("31", "울산광역시"),
    세종특별자치시("36", "세종특별자치시"),
    경기도("41", "경기도"),
    강원도("42", "강원도"),
    충청북도("43", "충청북도"),
    충청남도("44", "충청남도"),
    전라북도("45", "전라북도"),
    전라남도("46", "전라남도"),
    경상북도("47", "경상북도"),
    경상남도("48", "경상남도"),
    제주특별자치도("50", "제주특별자치도"),
    대학로("UNI", "대학로");

    private final String guCode;
    private final String guName;

    GuCodeEnum(final String guCode, final String guName) {
        this.guCode = guCode;
        this.guName = guName;
    }

    public static GuCodeEnum findByGuCode(String guCode) {
        return Arrays.stream(GuCodeEnum.values())
                .filter(guCodeEnum -> guCodeEnum.guCode.equals(guCode))
                .findAny()
                .orElseThrow(() -> new CustomException(ErrorCode.GU_CODE_NOT_FOUND));
    }
}
