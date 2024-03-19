package backend.likelion.todos.auth.jwt;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import backend.likelion.todos.common.UnAuthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;



@Service
public class JwtService {

    private final long accessTokenExpirationDayToMills;
    private final Algorithm algorithm;

    private Key key;

    public JwtService(JwtProperty jwtProperty) {
        this.accessTokenExpirationDayToMills =
                MILLISECONDS.convert(jwtProperty.accessTokenExpirationDay(), DAYS);
        this.algorithm = Algorithm.HMAC512(jwtProperty.secretKey());
    }

    // 회원 ID를 기반으로 JWT 토큰을 생성합니다.
    public String createToken(Long memberId) {
        // TODO [5단계] 현재 시간과 설정된 만료 시간을 사용하여 만료 날짜를 설정하세요.
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Date exp = new Date(nowMillis + 3600000);
        // TODO [5단계] memberId를 클레임으로 추가하세요.
        Claims claims = Jwts.claims();
        claims.put("userId", Long.toString(memberId));
        // TODO [5단계] 설정된 알고리즘으로 토큰을 서명하고 반환하세요.

        String jwt = Jwts.builder()
                .setSubject("likelion_김민지")
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key,SignatureAlgorithm.HS256)
                .setClaims(claims)
                .compact();

        return jwt;
    }

    // 토큰에서 회원 ID를 추출합니다.
    public Long extractMemberId(String token) {
        // TODO [5단계] 알고리즘을 사용해 토큰의 유효성을 검증하고, "memberId" 클레임에서 회원 ID를 추출하세요. 유효하지 않은 경우 "유효하지 않은 토큰입니다." 메시지와 함께 UnAuthorizedException을 발생시키세요.
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return  Long.parseLong(claims.get("userId").toString());
        }
        catch(Exception e){
            throw new UnAuthorizedException("유효하지 않은 토큰입니다.");
        }
    }
}
