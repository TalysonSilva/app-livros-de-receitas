package com.projeto.livros_de_receitas.DTO;

import com.projeto.livros_de_receitas.model.Ingrediente;
import com.projeto.livros_de_receitas.model.NivelDificuldade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ReceitaDTO(
        @NotNull
        @Size(min = 4, max = 100)
        String nome,
        @NotNull
        @Min(1)
        List<Ingrediente> ingredientes,
        @NotNull
        @Min(1)
        int tempoDeCozimento,
        @Min(1)
        int rendimento,
        NivelDificuldade nivelDificuldade,
        @Size(min = 0, max = 5)
        int estrelas,
        @Max(3)
        List<String> imagens) {
}
