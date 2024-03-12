package backend.likelion.todos.member.domain;

import backend.likelion.todos.common.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public boolean validateDuplicatedUsername(String username) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new ConflictException("이미 존재하는 아이디입니다.");
        }
        return true;
    }
}
