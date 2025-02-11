package com.projeto.livros_de_receitas.infra.security;

import com.projeto.livros_de_receitas.model.Usuario;
import com.projeto.livros_de_receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByEmail(username).orElseThrow(()
                ->  new UsernameNotFoundException("Usuario não encontrado"));

        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}
