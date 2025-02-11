package com.projeto.livros_de_receitas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Ingrediente> ingredientes;

    private int tempoDeCozimento;
    private int redimento;

    @Enumerated(EnumType.STRING)
    private NivelDificuldade nivelDificuldade;

    private int estrelas;

    @ElementCollection
    private  List<String> imagens;

    public Receita(String nome, List<Ingrediente> ingredientes, int tempoDeCozimento,
                   int redimento, NivelDificuldade nivelDificuldade, int estrelas, List<String> imagens) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.tempoDeCozimento = tempoDeCozimento;
        this.redimento = redimento;
        this.nivelDificuldade = nivelDificuldade;
        this.estrelas = estrelas;
        this.imagens = imagens;
    }
     public Receita (){

     }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public int getTempoDeCozimento() {
        return tempoDeCozimento;
    }

    public int getRedimento() {
        return redimento;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setTempoDeCozimento(int tempoDeCozimento) {
        this.tempoDeCozimento = tempoDeCozimento;
    }

    public void setRedimento(int redimento) {
        this.redimento = redimento;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Receita receita)) return false;
        return tempoDeCozimento == receita.tempoDeCozimento && redimento == receita.redimento && estrelas == receita.estrelas && Objects.equals(nome, receita.nome) && Objects.equals(ingredientes, receita.ingredientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ingredientes, tempoDeCozimento, redimento, estrelas);
    }
}
