package backend.likelion.todos.member.domain;

import lombok.Getter;
import backend.likelion.todos.member.domain.MemberRepository;

@Getter
public class Member {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String profileImageUrl;

    public Member(String username, String password, String nickname, String profileImageUrl) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void signup(MemberValidator validator) {
        if (validator.validateDuplicatedUsername(this.nickname)) {
            System.out.println("가입 성공 : " + this.nickname);
        } else {
            System.out.println("닉네임이 이미 사용 중입니다: " + this.nickname);
        }
    }
}
