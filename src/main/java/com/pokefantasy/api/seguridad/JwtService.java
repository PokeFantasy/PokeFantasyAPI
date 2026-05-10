package com.pokefantasy.api.seguridad;

import com.pokefantasy.api.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey key;

    @PostConstruct
    private void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(Usuario usuario) {
        Date ahora = new Date();
        Date caduca = new Date(ahora.getTime() + expirationMs);
        return Jwts.builder()
                .subject(String.valueOf(usuario.getIdUsuario()))
                .claim("email", usuario.getEmail())
                .claim("nombre", usuario.getNombre())
                .issuedAt(ahora)
                .expiration(caduca)
                .signWith(key)
                .compact();
    }

    public Claims parsearToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public int extraerIdUsuario(String token) {
        return Integer.parseInt(parsearToken(token).getSubject());
    }
}
