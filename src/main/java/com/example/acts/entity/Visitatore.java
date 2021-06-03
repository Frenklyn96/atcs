package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Visitatore {
    @Id
    private Long id;

    @ManyToOne
    private Gruppo gruppo;
    @NotNull
    private Boolean headphones;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @OneToMany(mappedBy = "visitatore")
    private Set<Posizione> posizioni;
    @ManyToMany
    @JoinTable(name = "visitatore_stanze",
            joinColumns = @JoinColumn(name = "visitatore_id"),
            inverseJoinColumns = @JoinColumn(name = "stanze_id"))
    private Set<Stanza> stanze;

    public Visitatore(){}



    public Visitatore(Long id, String nome, String cognome, Boolean headphones, Gruppo gruppo){
        this.id=id;
        this.nome= nome;
        this.cognome=cognome;
        this.headphones=headphones;
        this.gruppo=gruppo;
        this.stanze=new HashSet<Stanza>();
        this.posizioni = new HashSet<Posizione>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }

    public Boolean getHeadphones() {
        return headphones;
    }

    public void setHeadphones(Boolean headphones) {
        this.headphones = headphones;
    }

    public Set<Posizione> getPosizioni() {
        return posizioni;
    }

    public void setPosizioni(Set<Posizione> posizioni) {
        this.posizioni = posizioni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Set<Stanza> getStanze() {
        return stanze;
    }

    public void setStanze(Set<Stanza> stanze) {
        this.stanze = stanze;
    }

    public void addStanza(Stanza s) {
        stanze.add(s);
    }

    public void addGruppo(Gruppo g) {
        this.gruppo=g;
    }
}
