package com.checkshow.entity.constant;

import lombok.Getter;

@Getter
public enum DateEnum {

    월별("month", "월별"),
    주별("week", "주별"),
    일별("day", "일별");

    private final String dateCode;
    private final String dateName;

    DateEnum(String dateCode, String dateName) {
        this.dateCode = dateCode;
        this.dateName = dateName;
    }
}
