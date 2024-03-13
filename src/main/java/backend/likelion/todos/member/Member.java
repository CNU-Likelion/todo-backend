package backend.likelion.todos.member;

import backend.likelion.todos.common.UnAuthorizedException;
import lombok.Getter;

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

    // 로그인을 시도하고 비밀번호가 일치하지 않으면 UnAuthorizedException을 발생시킵니다.
    public void login(String password) {
        if(!password.equals(this.password)){
            throw new UnAuthorizedException("비밀번호가 일치하지 않습니다");
        }
    }

    public String getUserName(){
        return this.username;
    }

}
