package inhafood.inhamall.service;

import inhafood.inhamall.domain.Member;
import inhafood.inhamall.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        Member member1 = new Member();
        member1.setName("sony");
        member1.setLoginId("sonmh79");
        member1.setPassword("123");

        Long member1Id = memberService.join(member1);

        Member member2 = new Member();
        member2.setName("son");
        member2.setLoginId("sonmhd79");
        member2.setPassword("123");

        Long member2Id = memberService.join(member2);
    }
}