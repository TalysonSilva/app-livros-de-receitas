package com.projeto.livros_de_receitas.dto;


public record AvaliarReceitaDTORequest(
        String id,
        String nome,
        int estrelas) {
}
