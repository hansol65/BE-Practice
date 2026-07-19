package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.code.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.code.StoreErrorCode;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 가게에 리뷰 작성
    public ReviewResDTO.CreateReviewResult createReview(Long storeId, ReviewReqDTO.CreateReviewDTO dto) {
        // 로그인 기능X -> 하드코딩
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        Review review = ReviewConverter.toReview(
                dto,
                member,
                store
        );

        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toCreateReviewResult(savedReview);
    }
}
