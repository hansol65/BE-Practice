package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.umc9th.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override

    public Page<Review> findMyReviewsWithFilter(
            Long memberId,
            String storeName,
            Integer score,
            Pageable pageable
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if(storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.eq(storeName));
        }

        if(score != null) {
            if(score == 5) {
                builder.and(review.score.eq(5.0f));
            } else {
                builder.and(
                        review.score.goe(score.floatValue())
                                .and(review.score.lt(score + 1.0f))
                );
            }
        }

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();
        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
