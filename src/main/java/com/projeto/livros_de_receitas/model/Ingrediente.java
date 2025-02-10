package com.projeto.livros_de_receitas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    @JsonBackReference
    private Receita receita;
    public Ingrediente(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Ingrediente () {

    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Receita getReceita() {
        return receita;
    }

}
