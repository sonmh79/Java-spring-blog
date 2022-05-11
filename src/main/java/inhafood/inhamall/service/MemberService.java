package inhafood.inhamall.service;

import inhafood.inhamall.domain.Member;
import inhafood.inhamall.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembersByName = memberRepository.findByName(member.getName());
        if (!findMembersByName.isEmpty()) {
            throw new IllegalStateException("이름이 중복됩니다.");
        }

        List<Member> findMembersById = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembersById.isEmpty()) {
            throw new IllegalStateException("로그인 아이디가 중복됩니다.");
        }
    }

    public boolean loginCheck(String loginId, String pw) {
        List<Member> findMembers = memberRepository.findByLoginId(loginId);
        if (findMembers.isEmpty()) {
            throw new IllegalStateException("일치하는 아이디가 없습니다.");
        }
        Member findMember = findMembers.get(0);
        if (findMember.getPassword().equals(pw)) {
            return true;
        } else {
            return false;
        }
    }

}
