package com.projeto.livros_de_receitas.controller;

import com.projeto.livros_de_receitas.DTO.ReceitaDTO;
import com.projeto.livros_de_receitas.model.Receita;
import com.projeto.livros_de_receitas.repository.ReceitaRepository;
import com.projeto.livros_de_receitas.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/receita")
public class ReceitaController {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private ReceitaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity <String> cadastrarReceita(@Valid @RequestBody ReceitaDTO receitaDTO){
        String msg = service.cadastrarNovaReceita(receitaDTO);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Receita>>   visualizarTodasReceitas() {
        List<Receita> receitas = service.visualizarTodasReceitas();
        return new ResponseEntity<>(receitas, HttpStatus.OK );
    }

}
