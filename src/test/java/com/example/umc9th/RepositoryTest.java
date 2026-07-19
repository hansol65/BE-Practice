package com.example.umc9th;

import com.example.umc9th.config.QueryDslConfig;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.enums.Address;
import com.example.umc9th.domain.store.repository.LocationRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.enums.SocialType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest(properties = {
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})

//@EnableJpaAuditing
@Import(QueryDslConfig.class)

public class RepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    MemberMissionRepository memberMissionRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    LocationRepository locationRepository;

    @Test
    @DisplayName("마이페이지 조회 - 회원 ID로 탈퇴하지 않은 회원을 조회한다")
    void findMyPage() {
        Member member = Member.builder()
                .name("한솔")
                .gender(Gender.MALE)
                .birth(LocalDate.of(2001, 8, 3))
                .address(Address.JONGNO)
                .detailAddress("901호")
                .socialUid("whgksthf65")
                .socialType(SocialType.GOOGLE)
                .point(1000)
                .email("whgksthf65@gmail.com")
                .phoneNumber("01057670773")
                .build();
        Member savedMember = memberRepository.save(member);

        Optional<Member> result = memberRepository.findByIdAndDeletedAtIsNull(savedMember.getId());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getName()).isEqualTo("한솔");
        Assertions.assertThat(result.get().getPoint()).isEqualTo(1000);
    }

    @Test
    @DisplayName("홈 화면 조회 - 선택한 지역의 미션 목록을 페이징 조회한다")
    void findHomeMissionsByLocation() {
        // given
        Location location = locationRepository.save(
                Location.builder()
                        .name("강남")
                        .build()
        );

        Store store = storeRepository.save(
                Store.builder()
                        .name("강남맛집")
                        .managerNumber(101L)
                        .detailAddress("강남역")
                        .location(location)
                        .build()
        );

        missionRepository.save(
                Mission.builder()
                        .deadline("2026-12-31")
                        .conditional("10000원 이상 먹기")
                        .point(500)
                        .store(store)
                        .build()
        );

        missionRepository.save(
                Mission.builder()
                        .deadline("2026-12-31")
                        .conditional("리뷰 작성하기")
                        .point(300)
                        .store(store)
                        .build()
        );

        PageRequest pageable = PageRequest.of(0, 10);

        // when
        Page<Mission> result = missionRepository.findByStoreLocationName("강남", pageable);

        // then
        Assertions.assertThat(result.getContent()).hasSize(2);
        Assertions.assertThat(result.getContent())
                .extracting("point")
                .containsExactlyInAnyOrder(500, 300);
    }

    @Test
    @DisplayName("내 미션 조회 - 진행중 미션과 완료 미션을 구분해서 조회한다")
    void findMyMissionsByStatus() {
        // given
        Member member = memberRepository.save(
                Member.builder()
                        .name("한솔")
                        .gender(Gender.NONE)
                        .birth(LocalDate.of(1996, 6, 29))
                        .address(Address.GANGNAM)
                        .detailAddress("101호")
                        .socialUid("kakao-123")
                        .socialType(SocialType.KAKAO)
                        .point(1000)
                        .email("test@test.com")
                        .phoneNumber("01012345678")
                        .build()
        );

        Location location = locationRepository.save(
                Location.builder()
                        .name("강남")
                        .build()
        );

        Store store = storeRepository.save(
                Store.builder()
                        .name("강남맛집")
                        .managerNumber(101L)
                        .detailAddress("강남역")
                        .location(location)
                        .build()
        );

        Mission mission1 = missionRepository.save(
                Mission.builder()
                        .deadline("2026-12-31")
                        .conditional("10000원 이상 먹기")
                        .point(500)
                        .store(store)
                        .build()
        );

        Mission mission2 = missionRepository.save(
                Mission.builder()
                        .deadline("2026-12-31")
                        .conditional("리뷰 작성하기")
                        .point(300)
                        .store(store)
                        .build()
        );

        memberMissionRepository.save(
                MemberMission.builder()
                        .member(member)
                        .mission(mission1)
                        .isComplete(false)
                        .build()
        );

        memberMissionRepository.save(
                MemberMission.builder()
                        .member(member)
                        .mission(mission2)
                        .isComplete(true)
                        .build()
        );

        PageRequest pageable = PageRequest.of(0, 10);

        // when
        Page<MemberMission> progressing =
                memberMissionRepository.findByMemberIdAndIsComplete(member.getId(), false, pageable);

        Page<MemberMission> completed =
                memberMissionRepository.findByMemberIdAndIsComplete(member.getId(), true, pageable);

        // then
        Assertions.assertThat(progressing.getContent()).hasSize(1);
        Assertions.assertThat(progressing.getContent().get(0).getIsComplete()).isFalse();

        Assertions.assertThat(completed.getContent()).hasSize(1);
        Assertions.assertThat(completed.getContent().get(0).getIsComplete()).isTrue();
    }

    @Test
    @DisplayName("특정 회원의 전체 미션을 조회한다")
    void findAllMemberMissions() {
        // given
        Member member = memberRepository.save(
                Member.builder()
                        .name("한솔")
                        .gender(Gender.MALE)
                        .birth(LocalDate.of(2001, 8, 3))
                        .address(Address.GANGNAM)
                        .detailAddress("901호")
                        .socialUid("whgksthf65")
                        .socialType(SocialType.GOOGLE)
                        .point(1000)
                        .email("whgksthf65@gmail.com")
                        .phoneNumber("01057670773")
                        .build()
        );

        Location location = locationRepository.save(
                Location.builder()
                        .name("강남")
                        .build()
        );

        Store store = storeRepository.save(
                Store.builder()
                        .name("강남맛집")
                        .managerNumber(101L)
                        .detailAddress("강남역")
                        .location(location)
                        .build()
        );

        Mission mission = missionRepository.save(
                Mission.builder()
                        .deadline("2026-12-31")
                        .conditional("10000원 이상 먹기")
                        .point(500)
                        .store(store)
                        .build()
        );

        memberMissionRepository.save(
                MemberMission.builder()
                        .member(member)
                        .mission(mission)
                        .isComplete(false)
                        .build()
        );

        PageRequest pageable = PageRequest.of(0, 10);

        // when
        Page<MemberMission> result =
                memberMissionRepository.findByMemberId(member.getId(), pageable);

        // then
        Assertions.assertThat(result.getContent()).hasSize(1);
    }
}
