package backend.likelion.todos.member;

import static org.springframework.http.HttpStatus.CREATED;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(CREATED)
    @PostMapping
    // 회원 가입 요청을 처리합니다.
    public void signup(@RequestBody SignupRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String nickname = request.getNickname();
        String profileImageUrl = request.getProfileImageUrl();
        memberService.signup(username, password, nickname, profileImageUrl);
    }
}
