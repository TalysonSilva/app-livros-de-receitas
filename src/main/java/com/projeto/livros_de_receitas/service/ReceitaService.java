package com.projeto.livros_de_receitas.service;

import com.projeto.livros_de_receitas.DTO.ReceitaDTO;
import com.projeto.livros_de_receitas.model.Ingrediente;
import com.projeto.livros_de_receitas.model.Receita;
import com.projeto.livros_de_receitas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private  ReceitaRepository receitaRepository;

    public String cadastrarNovaReceita(ReceitaDTO receitaDTO) {
        Receita novaReceita = new Receita(
                receitaDTO.nome(),
                receitaDTO.ingredientes(),
                receitaDTO.tempoDeCozimento(),
                receitaDTO.rendimento(),
                receitaDTO.nivelDificuldade(),
                receitaDTO.estrelas(),
                receitaDTO.imagens()
        );
        List<Ingrediente> ingredientes =  converterDtoEntidadeIngredientes(receitaDTO, novaReceita);

        novaReceita.setIngredientes(ingredientes);

        receitaRepository.save(novaReceita);
        return "Nova Receita Cadastrado com sucesso";
    }

    private  List<Ingrediente> converterDtoEntidadeIngredientes(ReceitaDTO receitaDTO, Receita novaReceita) {
        List<Ingrediente> ingredientes = receitaDTO.ingredientes().stream()
                .map(ingredienteDTO -> {
                    Ingrediente ingrediente = new Ingrediente(
                            ingredienteDTO.getNome(),
                            ingredienteDTO.getQuantidade()
                    );
                    ingrediente.setReceita(novaReceita);  // Associando o ingrediente à receita
                    return ingrediente;
                })
                .collect(Collectors.toList());
        return ingredientes;

    }


    public List<Receita> visualizarTodasReceitas() {
        return receitaRepository.findAll();
    }
}
