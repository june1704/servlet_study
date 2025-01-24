package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {

    private Key key;
    private static JwtProvider instance;

    private JwtProvider() {
        final String SECRET = "af2ec0c091d9b8e2026f796da1f96fa34e0eb85caa747266ebe16c13218b1287291cdd91a043c82e08bec7e7c819b44497f52e84b74de4f6a1e78daed541f18e88bf494bf558075cc6efcb24b4ec40b7641dfe97e1d70f40bbcc60d5123f194e81602856e052b26e44f960021a441e3fcab7cca3f4691c9619e934a4c2ec81404de18de0fba360d1a846046f8eead53e195f24a68689bcb103f8c74ff4678eceae13109b6cce223dce6abae4e2cd3a798945bf1135d678477752845c5d9d86ce20eebf742f1ba0b4649b9d5f56d9cef526504dc636ffc6aab7ed2e6d61694cfde19999388e49d78b0fd524ab0fc676e88530e7060b05f71b8f2da24b02b5f38c";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpireData() {
        return new Date(new Date().getTime() + (1000l) * 60 * 60 * 24 * 365);
    }
    // 토큰 생성
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .setExpiration(getExpireData())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        Claims claims = null;
        try {
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build();

            claims = jwtParser
                    .parseClaimsJws(removeBearer(token))
                    .getBody();

//            claims = Jwts.parserBuilder() // JWT를 파싱할 객체를 생성
//                    .setSigningKey(key)  // 토큰을 검증하기 위해 사용할 서명 키를 설정
//                    .build()             // 설정된 파서를 빌드
//                    .parseClaimsJws(removeBearer(token)) // JWT를 파싱하여 서명을 검증하고
//                    .getBody();          // 토큰의 Claims(본문) 부분을 가져옴
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private String removeBearer(String bearertoken) {
        String accessToken = null;
        final String BEARER_KEYWORD = "Bearer ";
        if(bearertoken.startsWith(BEARER_KEYWORD)) {
            accessToken = bearertoken.substring(BEARER_KEYWORD.length());
        }
        return accessToken;
    }
}
