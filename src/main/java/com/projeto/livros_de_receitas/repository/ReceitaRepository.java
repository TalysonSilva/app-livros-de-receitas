package com.projeto.livros_de_receitas.repository;

import com.projeto.livros_de_receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository <Receita, String> {

}
