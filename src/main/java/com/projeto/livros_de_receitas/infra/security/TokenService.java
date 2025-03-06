package com.projeto.livros_de_receitas.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.projeto.livros_de_receitas.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token}")
    private String secretKey;

    public String gerandoToken (Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("livros-de-receitas")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeValidade())
                    .sign(algorithm);

            return token;

        }catch ( JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token ");

        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("livros-de-receitas")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return null;
        }
    }

    // Token Valido por 2 horas
    private Instant gerarDataDeValidade() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
