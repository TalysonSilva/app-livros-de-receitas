package com.projeto.livros_de_receitas.controller;

import com.projeto.livros_de_receitas.dto.*;
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
    public ResponseEntity <String> cadastrarReceita(@Valid @RequestBody CadastroReceitaDTO cadastroReceitaDTO){
        String msg = service.cadastrarNovaReceita(cadastroReceitaDTO);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Receita>>   visualizarTodasReceitas() {
        List<Receita> receitas = service.visualizarTodasReceitas();
        return new ResponseEntity<>(receitas, HttpStatus.OK );
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Receita>> visualizarReceitasDoUsuario(@RequestParam String email){
        List<Receita> receitas = service.visualizarReceitasDoUsuario(email);
        if (!receitas.isEmpty()) {return new ResponseEntity<>(receitas,HttpStatus.OK );}
        else return new ResponseEntity<>(receitas,HttpStatus.NOT_FOUND );

    }
   @DeleteMapping("/deletar")
   public ResponseEntity<String> deletarReceita(@RequestBody ReceitaDTORequest receitaDTO ){
        var msg = service.deletarReceitaDoUsuario(receitaDTO);
      return  new ResponseEntity<>(msg, HttpStatus.OK);
  }

  @PutMapping("/avaliar")
  public String avaliarReceita(@RequestBody AvaliarReceitaDTORequest avaliarReceita) {
        var msg =  service.avaliarReceita(avaliarReceita);
        return msg;
  }

  @GetMapping("/")
  public  ResponseEntity<Receita> buscarReceita (@RequestParam String id) {
        Receita receita= service.buscarReceitaPorId(id);
        return new ResponseEntity<>(receita, HttpStatus.OK);
  }


}
