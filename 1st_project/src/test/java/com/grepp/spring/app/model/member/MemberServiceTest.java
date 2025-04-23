package com.grepp.spring.app.model.member;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.grepp.spring.app.model.member.dto.Member;
import com.grepp.spring.infra.error.exceptions.CommonException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    
    @Mock
    private MemberRepository memberRepository;
    
    @InjectMocks
    private MemberService memberService;

    @Test
    public void signup(){
        // given : 테스트를 위한 데이터 및 mock 객체의 행위를 설정
        given(memberRepository.existsMember(anyString()))
            .willAnswer(invocation -> {
                String userId = invocation.getArgument(0);
                return userId.equals("duplicated");
            });
        
        Member member = new Member();
        member.setUserId("test");
        
        // when : 테스트 실행
        // then : 테스트 검증
        memberService.signup(member);
    }
    
    // 메서드 이름 - 결과 - 이유
    // BDD
    @Test
    public void signup_throwException_existsName(){
        // given : 테스트를 위한 데이터 및 mock 객체의 행위를 설정
        given(memberRepository.existsMember(anyString()))
            .willAnswer(invocation -> {
                String userId = invocation.getArgument(0);
                return userId.equals("duplicated");
            });
        
        Member member = new Member();
        member.setUserId("duplicated");

        // when - then
        assertThatThrownBy(() -> memberService.signup(member))
            .isInstanceOf(CommonException.class);
    }
}