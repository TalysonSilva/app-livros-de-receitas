package com.projeto.livros_de_receitas.infra.security;

import com.projeto.livros_de_receitas.model.Usuario;
import com.projeto.livros_de_receitas.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class FiltroSecurity extends OncePerRequestFilter {

    @Autowired
     TokenService tokenService;

    @Autowired
      UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);
        var login = tokenService.validarToken(token);

        if (login != null) {
            Usuario usuario = usuarioRepository.findByEmail(login).orElseThrow(()
                    -> new RuntimeException("Usuario não encotrado"));
            var autorizacao = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USUARIO"));
            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, autorizacao );
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
