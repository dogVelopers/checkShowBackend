package com.checkshow.entity;

import com.checkshow.entity.constant.StateEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * State Entity
 * id : PK
 * comment : 설명
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false)
    private String comment;

    @Builder
    public State(Short id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public StateEnum toEnum() {
        return StateEnum.findById(this.id);
    }
}
