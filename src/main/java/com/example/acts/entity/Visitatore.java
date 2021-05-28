package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Visitatore {
    @Id
    private Long id;
    @NotNull
    private Date oraInizio;
    @NotNull
    private Date oraFine;
    @OneToOne
    private Gruppo gruppo;
    @NotNull
    private Boolean headphones;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @OneToMany(mappedBy = "visitatore")
    private Set<Posizione> posizioni;
    @ManyToOne
    private Stanza stanza;

    public Visitatore(){}



    public Visitatore(Long id, String nome, String cognome, Date oraInizio, Date oraFine, Boolean headphones, Gruppo gruppo, Stanza stanza){
        this.id=id;
        this.nome= nome;
        this.cognome=cognome;
        this.oraInizio = oraInizio;
        this.oraFine=oraFine;
        this.headphones=headphones;
        this.gruppo=gruppo;
        this.stanza=stanza;
        posizioni = new TreeSet<Posizione>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Date oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Date getOraFine() {
        return oraFine;
    }

    public void setOraFine(Date oraFine) {
        oraFine = oraFine;
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

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
}
