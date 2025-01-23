package com.xplug.tech.commons.security.jwt;

import com.xplug.tech.commons.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //  @Value("${nyasha.app.jwtSecret}")
    private final String jwtSecret = "ThisisthesecretkeyfrbonviemedicalsocietyitmustbeverylongjghjbjhkjnuyhbYHBVCXVBCDSDRfgcdxfcgvgYUHBNBVCXDSFRTG";


    //  @Value("${nyasha.app.jwtExpiration}")
    private final int jwtExpiration = 120000;

    public static Key getKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    public String generateJwtToken(Authentication authentication, Collection<? extends GrantedAuthority> authorities) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        Claims claims = Jwts.claims();
        claims.put("subject", userPrincipal.getUsername());
        claims.put("username", userPrincipal.getUsername());
        claims.put("issuedAt", new Date());
        claims.put("expiration", new Date((new Date()).getTime() + jwtExpiration * 1000L));
        claims.put("authorities", (Arrays.stream(authorities.toArray())
                .map(Object::toString)
                .toArray(String[]::new)));

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000L))
                .setClaims(claims)
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {

        String respone = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();

        return "admin";
    }

    public String decodeAccessToken(String accessToken) {

        if (accessToken == null) {
            return null;
        }

        org.springframework.security.jwt.Jwt jwt = JwtHelper.decode(accessToken);
        String claims = jwt.getClaims();
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> claimMap = jsonParser.parseMap(claims);
        Integer exp = (Integer) claimMap.get("exp");
        int now = (int) (System.currentTimeMillis() / 1000L);

//        if (exp < now) {
//            retrieveNewAccessToken();
//        }

        String username = (String) claimMap.get("username");
        return username;
    }
}
