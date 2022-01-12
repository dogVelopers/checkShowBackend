package com.checkshow.entity.constant;

import java.util.Arrays;

public enum StateEnum {
    공연예정(1, "공연예정"),
    공연중(2, "공연중"),
    공연완료(3, "공연완료");

    private final int id;
    private final String comment;

    StateEnum(final int id, final String comment) {
        this.id = id;
        this.comment = comment;
    }

    public static StateEnum findById(int input) {
        return Arrays.stream(StateEnum.values())
                .filter(stateEnum -> stateEnum.id == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("id로 데이터를 찾을 수 없습니다."));
    }

    public static StateEnum findByComment(String input) {
        return Arrays.stream(StateEnum.values())
                .filter(stateEnum -> stateEnum.comment.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("comment로 데이터를 찾을 수 없습니다."));
    }
}
