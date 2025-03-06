package com.projeto.livros_de_receitas.service;

import com.projeto.livros_de_receitas.dto.CadastroReceitaDTO;
import com.projeto.livros_de_receitas.model.Ingrediente;
import com.projeto.livros_de_receitas.model.Receita;
import com.projeto.livros_de_receitas.model.Usuario;
import com.projeto.livros_de_receitas.repository.ReceitaRepository;
import com.projeto.livros_de_receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private  ReceitaRepository receitaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String cadastrarNovaReceita(CadastroReceitaDTO cadastroReceitaDTO) {
        Receita novaReceita = new Receita(
                cadastroReceitaDTO.nome(),
                cadastroReceitaDTO.ingredientes(),
                cadastroReceitaDTO.tempoDeCozimento(),
                cadastroReceitaDTO.rendimento(),
                cadastroReceitaDTO.nivelDificuldade(),
                cadastroReceitaDTO.imagens(),
                cadastroReceitaDTO.emailUsuario()
        );

          Usuario usuario = usuarioRepository.findByEmail(cadastroReceitaDTO.emailUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        List<Ingrediente> ingredientes =  converterDtoEntidadeIngredientes(cadastroReceitaDTO, novaReceita);

        novaReceita.setIngredientes(ingredientes);

        receitaRepository.save(novaReceita);
        return "Nova Receita Cadastrado com sucesso";
    }

    private  List<Ingrediente> converterDtoEntidadeIngredientes(CadastroReceitaDTO cadastroReceitaDTO, Receita novaReceita) {
        List<Ingrediente> ingredientes = cadastroReceitaDTO.ingredientes().stream()
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
