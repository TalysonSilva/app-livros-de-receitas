package com.projeto.livros_de_receitas.repository;

import com.projeto.livros_de_receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository <Receita, String> {

    List<Receita> findAllByEmailUsuario(String emailUsuario);

    Optional<Receita> findByNomeAndEmailUsuario(String nome, String emailUsuario);

    Optional<Receita> findByNomeAndId(String nome, String id);

    Optional<Receita> findByNomeAndModoPreparo(String nome, String s);
}
