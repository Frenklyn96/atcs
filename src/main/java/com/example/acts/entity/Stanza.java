package com.example.acts.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Stanza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany
    private List<Visitatore> visitatori;

    @OneToMany
    private List<Gruppo> Gruppi;

    public Stanza(){}

    public Stanza(String nome) {
        this.nome = nome;
        this.visitatori = new ArrayList<>();
        this.Gruppi=new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Visitatore> getVisitatori() {
        return visitatori;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVisitatori(List<Visitatore> visitatori) {
        this.visitatori = visitatori;
    }


}
