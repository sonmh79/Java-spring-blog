package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember(){
        Member member = new Member();
        member.setName("MinHyeok");
        member.setLoginId("sonmh79");
        member.setPassword("1234");

        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedId);
        org.assertj.core.api.Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }


}