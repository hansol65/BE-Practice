package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    Page<ReviewResDTO.MyReview> getMyReviews(
            Long memberId,
            String storeName,
            Integer score,
            Pageable pageable
    );
}
