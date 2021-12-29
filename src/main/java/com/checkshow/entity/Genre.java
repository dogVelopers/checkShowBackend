package com.checkshow.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Genre Entity
 * id : PK
 * comment : 설명
 * detailComment : 자세한 설명
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String detailComment;

    @Builder
    public Genre(Short id, String comment, String detailComment) {
        this.id = id;
        this.comment = comment;
        this.detailComment = detailComment;
    }
}
