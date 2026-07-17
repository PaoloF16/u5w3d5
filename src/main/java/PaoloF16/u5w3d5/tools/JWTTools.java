package PaoloF16.u5w3d5.tools;

import PaoloF16.u5w3d5.entities.User;
import PaoloF16.u5w3d5.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;


    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7))
                .subject(String.valueOf(user.getId()))
                .signWith(getSigningKey())
                .compact();
    }

    public void verifyToken(String accessToken) {
        try {

            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(accessToken);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problems with your token"); // 401
        }
    }

    public String extractIdFromToken(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload()
                    .getSubject();
        } catch (Exception ex) {
            throw new UnauthorizedException("Invalid token or impossible to extract ID");
        }
    }
}