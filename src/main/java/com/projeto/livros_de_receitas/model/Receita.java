package com.projeto.livros_de_receitas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.lang.String id;

    private java.lang.String nome;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Ingrediente> ingredientes;

    private int tempoDeCozimento;
    private int rendimento;

    @Enumerated(EnumType.STRING)
    private NivelDificuldade nivelDificuldade;

    private String emailUsuario;

    private int estrelas;

    @ElementCollection
    private  List<java.lang.String> imagens;

    public Receita(java.lang.String nome, List<Ingrediente> ingredientes, int tempoDeCozimento,
                   int rendimento, NivelDificuldade nivelDificuldade,
                   List<java.lang.String> imagens, String emailUsuario) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.tempoDeCozimento = tempoDeCozimento;
        this.rendimento = rendimento;
        this.nivelDificuldade = nivelDificuldade;
        this.imagens = imagens;
        this.emailUsuario = emailUsuario;
    }

    public Receita (){}


    public java.lang.String getId() {
        return id;
    }

    public java.lang.String getNome() {
        return nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public int getTempoDeCozimento() {
        return tempoDeCozimento;
    }

    public int getRendimento() {
        return rendimento;
    }


    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setTempoDeCozimento(int tempoDeCozimento) {
        this.tempoDeCozimento = tempoDeCozimento;
    }

    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }

    public List<java.lang.String> getImagens() {
        return imagens;
    }

    public void setImagens(List<java.lang.String> imagens) {
        this.imagens = imagens;
    }


    public void setNivelDificuldade(NivelDificuldade nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public String getUsuario() {
        return emailUsuario;
    }

    public void setUsuario(String string) {
        this.emailUsuario = string;
    }

    public NivelDificuldade getNivelDificuldade() {
        return nivelDificuldade;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }
}
