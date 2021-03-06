package com.example.acts.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Stanza {

    @Id
    private Long id;

    @NotBlank
    private String nome;

    @ManyToMany(mappedBy = "stanze")
    private Set<Visitatore> visitatori;

    @ManyToMany(mappedBy = "stanze")
    private Set<Gruppo> gruppi;

    public Stanza(){}

    public Stanza(Long id,String nome) {
        this.id=id;
        this.nome = nome;
        this.visitatori = new HashSet<Visitatore>();
        this.gruppi=new HashSet<Gruppo>();

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<Visitatore> getVisitatori() {
        return visitatori;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVisitatori(Set<Visitatore> visitatori) {
        this.visitatori = visitatori;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Gruppo> getGruppi() {
        return gruppi;
    }

    public void setGruppi(Set<Gruppo> gruppi) {
        this.gruppi = gruppi;
    }

    public void addGruppo(Gruppo g) {
        gruppi.add(g);
    }

    public void addVisitatore(Visitatore v) {
        visitatori.add(v);
    }
}
