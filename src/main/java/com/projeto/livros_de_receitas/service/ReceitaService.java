package com.projeto.livros_de_receitas.service;

import com.projeto.livros_de_receitas.dto.*;
import com.projeto.livros_de_receitas.model.Ingrediente;
import com.projeto.livros_de_receitas.model.Receita;
import com.projeto.livros_de_receitas.model.Usuario;
import com.projeto.livros_de_receitas.repository.ReceitaRepository;
import com.projeto.livros_de_receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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
                cadastroReceitaDTO.emailUsuario(),
                cadastroReceitaDTO.modoPreparo()
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

    //Visualizar receitas apenas do usuario
    public List<Receita> visualizarReceitasDoUsuario(String email) {

        if (usuarioRepository.existsByEmail(email)) {
            return receitaRepository.findAllByEmailUsuario(email);
        }else {
            return null;
        }

    }

    public String deletarReceitaDoUsuario(ReceitaDTORequest receitaDTO) {

        //Procurando a receita banco da dado utilizando email e nome da receita como paramentros
        Optional<Receita> receitaEncontrada = receitaRepository.findByNomeAndEmailUsuario(receitaDTO.nome(),
              receitaDTO.emailUsuario());

        if (receitaEncontrada.isPresent()){
            receitaRepository.deleteById(receitaEncontrada.get().getId());
            return "Receita apagada com sucesso";
        }

        return "Receita não existe";
    }

    public String avaliarReceita(AvaliarReceitaDTORequest receitaDTORequest) {

        if (receitaDTORequest.estrelas() < 1 || receitaDTORequest.estrelas()  > 5) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "A pontuação passada incorreta");
        }

        Optional<Receita> receitaExiste = receitaRepository.findByNomeAndId(receitaDTORequest.nome(),
                receitaDTORequest.id());

        if (receitaExiste.isPresent()) {
            Receita receita = receitaExiste.get();
            receita.setEstrelas(receita.getEstrelas() + receitaDTORequest.estrelas());
            receitaRepository.save(receita);
            return "Receita Avalidada com sucesso";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receita invalida");

    }

    public Receita buscarReceitaPorId(String id) {
        return receitaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
    }
}
