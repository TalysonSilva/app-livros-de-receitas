package com.projeto.livros_de_receitas.controller;

import com.projeto.livros_de_receitas.dto.CadastrarNovoUsuario;
import com.projeto.livros_de_receitas.dto.LoginUsuarioDTO;
import com.projeto.livros_de_receitas.dto.ResponseLoginDTO;
import com.projeto.livros_de_receitas.infra.security.TokenService;
import com.projeto.livros_de_receitas.model.Usuario;
import com.projeto.livros_de_receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(("/api/usuario"))
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService service;


    @PostMapping("/login")
    public ResponseEntity autenticacaoUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        Usuario usuario = repository.findByEmail(loginUsuarioDTO.email()).orElseThrow(()
                -> new RuntimeException("Usuario não encotrado"));

        if (passwordEncoder.matches(loginUsuarioDTO.senha(), usuario.getSenha())) {
            String token = service.gerandoToken(usuario);
            return  ResponseEntity.ok( new ResponseLoginDTO(token, usuario.getNome()));
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/registrar")
    public ResponseEntity autenticacaoUsuario(@RequestBody CadastrarNovoUsuario cadastrarNovoUsuario) {

        Optional<Usuario> usuario = repository.findByEmail(cadastrarNovoUsuario.email());

        if (usuario.isEmpty()) {
         Usuario novoUsuario = new Usuario();
         novoUsuario.setSenha(passwordEncoder.encode(cadastrarNovoUsuario.senha()));
         novoUsuario.setEmail(cadastrarNovoUsuario.email());
         novoUsuario.setNome(cadastrarNovoUsuario.nome());

         repository.save(novoUsuario);

         String token = service.gerandoToken(novoUsuario);
         return  ResponseEntity.ok( new ResponseLoginDTO(token, novoUsuario.getNome()));

        }

        return ResponseEntity.badRequest().build();

    }
}
