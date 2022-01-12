package com.checkshow.entity;

import com.checkshow.entity.constant.GenreEnum;
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
    private String code;

    @Column(nullable = false)
    private String detailCode;

    @Builder
    public Genre(Short id, String code, String detailCode) {
        this.id = id;
        this.code = code;
        this.detailCode = detailCode;
    }

    public GenreEnum toEnum() {
        return GenreEnum.findById(this.id);
    }
}
