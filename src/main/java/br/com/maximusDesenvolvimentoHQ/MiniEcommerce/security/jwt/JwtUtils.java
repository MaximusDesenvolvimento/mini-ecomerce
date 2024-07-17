package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.security.jwt;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Log4j2
@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationMs}")
    private int jwtExpirationMs;



    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public SecretKey getSigninKey(){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getUsernameToken(String token){
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            log.info("validate: "+authToken);
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException e){
            log.info("Token inválido");
        }catch (ExpiredJwtException e){
            log.info("Token expirado");
        }catch (UnsupportedJwtException e){
            log.info("Token não suportado");
        }catch (IllegalArgumentException e){
            log.info("Token argumetno inválido");
        }
        return false;
    }
}
