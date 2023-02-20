package proyecto.proyecto_vacunacion.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET="aNW9A0UOmCHt3ERTFVUB";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS=2_592_000l;


    public static String createToken(String usuario,String contrasenia){
        long expirationTime=ACCESS_TOKEN_VALIDITY_SECONDS *1_000;
        Date expirationDate= new Date(System.currentTimeMillis() +expirationTime);

        Map<String,Object> extra=new HashMap<>();
        extra.put("contrasenia",contrasenia);

        return Jwts.builder()
                .setSubject(usuario)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
    public  static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String usuario = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(usuario,null, Collections.emptyList());

        }catch (JwtException e){
            return  null;
        }
    }


}
