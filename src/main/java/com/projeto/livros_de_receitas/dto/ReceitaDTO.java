package com.projeto.livros_de_receitas.dto;

import com.projeto.livros_de_receitas.model.Ingrediente;
import com.projeto.livros_de_receitas.model.NivelDificuldade;

import java.util.List;

public record ReceitaDTO(
        String id,
        String nome,
        List<Ingrediente> ingredientes,
        int tempoDeCozimento,
        int rendimento,
        NivelDificuldade nivelDificuldade,
        List<String> imagens,
        String modoPreparo
) {
}
