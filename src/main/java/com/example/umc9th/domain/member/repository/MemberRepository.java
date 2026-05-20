package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    // 마이페이지 조회
    Optional<Member> findByIdAndDeletedAtIsNull(Long memberId);
    // 이름으로 탈퇴하지 않은 회원 조회
    Optional<Member> findByNameAndDeletedAtIsNull(String name);
}
