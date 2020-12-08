package com.escapenavigator.slot.security;

import com.escapenavigator.slot.config.AppProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AppProperties appProperties;

    public UsernamePasswordAuthenticationToken getAuthenticationFromJwt(String jwt) {
        return new UsernamePasswordAuthenticationToken("USER", jwt);
    }

    public boolean validateJwt(String jwt) {
        try {
            String tokenSecret = appProperties.getAuth().getTokenSecret();
            String subject = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwt).getBody().getSubject();
            return subject != null;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string not valid");
        }
        return false;
    }

}
