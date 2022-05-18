package inhafood.inhamall.service;

import inhafood.inhamall.domain.Member;
import inhafood.inhamall.exception.LoginIdAlreadyExistException;
import inhafood.inhamall.exception.NameAlreadyExistException;
import inhafood.inhamall.exception.NoSuchLoginIdException;
import inhafood.inhamall.exception.PasswordNotMatchException;
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
            throw new NameAlreadyExistException();
        }

        List<Member> findMembersById = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembersById.isEmpty()) {
            throw new LoginIdAlreadyExistException();
        }
    }

    public Member loginCheck(String loginId, String pw){
        List<Member> findMembers = memberRepository.findByLoginId(loginId);
        if (findMembers.isEmpty()) {
            throw new NoSuchLoginIdException();
        }
        Member findMember = findMembers.get(0);
        if (findMember.getPassword().equals(pw)) {
            return findMember;
        } else {
            throw new PasswordNotMatchException();
        }
    }

    public Member findOne(Long id) {
        Member findMember = memberRepository.findById(id);
        return findMember;
    }
}
