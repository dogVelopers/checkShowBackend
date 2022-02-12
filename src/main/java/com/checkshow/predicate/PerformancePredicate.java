package com.checkshow.predicate;

import com.checkshow.entity.Genre;
import com.checkshow.entity.QPerformance;
import com.checkshow.entity.State;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class PerformancePredicate {

    public static Predicate search(Genre genre, State state, Byte age) {
        QPerformance performance = QPerformance.performance;

        BooleanBuilder builder = new BooleanBuilder();

        if (genre != null) {
            builder.and(performance.genre.eq(genre));
        }

        if (state != null) {
            builder.and(performance.state.eq(state));
        }

        if (age != null) {
            builder.and(performance.age.lt(age));
        }

        return builder;
    }
}
