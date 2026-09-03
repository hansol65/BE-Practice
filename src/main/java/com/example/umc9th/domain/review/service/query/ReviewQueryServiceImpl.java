package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewResDTO.MyReview> getMyReviews(
            Long memberId,
            String storeName,
            Integer score,
            Integer page
    ) {
        if(score != null && (score < 1 || score > 5)) {
            throw new ReviewException (
                    ReviewErrorCode.INVALID_SCORE
            );
        }
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        return reviewRepository.findMyReviewsWithFilter(
                memberId,
                storeName,
                score,
                pageRequest
        ).map(ReviewConverter::toMyReviewDTO);
    }
}
