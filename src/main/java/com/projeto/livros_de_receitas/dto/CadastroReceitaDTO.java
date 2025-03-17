package com.projeto.livros_de_receitas.dto;

import com.projeto.livros_de_receitas.model.Ingrediente;
import com.projeto.livros_de_receitas.model.NivelDificuldade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CadastroReceitaDTO(
        @NotNull
        @Size(min = 4, max = 100)
        String nome,
        @NotNull
        @Min(2)
        List<Ingrediente> ingredientes,
        @NotNull
        @Min(1)
        int tempoDeCozimento,
        @Min(1)
        int rendimento,
        NivelDificuldade nivelDificuldade,
        @Max(3)
        List<String> imagens,
        @NotNull
        String modoPreparo,
        String emailUsuario)
{
}
