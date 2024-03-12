package backend.likelion.todos.member.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private final Map<Long, Member> members = new HashMap<>();
    private Long sequence = 1L;

    public Member save(Member member) {
        member.setId(sequence);
        sequence++;
        members.put(sequence, member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = members.get(id);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByUsername(String username) {
        return members.values()
            .stream()
            .filter(member -> member.getUsername().equals(username))
            .findAny();
    }

    public void clear() {
        this.members.clear();
    }
}
