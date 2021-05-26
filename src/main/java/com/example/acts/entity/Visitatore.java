package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Visitatore {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private Date oraInizio;
    @NotBlank
    private Date OraFine;
    @OneToOne
    private Gruppo gruppo;
    @NotBlank
    private Boolean headphones;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @OneToMany
    private List<Posizione> posizioni;

    public Visitatore(String nome, String cognome, Date oraInizio, Boolean headphones) {
        this.nome= nome;
        this.cognome=cognome;
        this.oraInizio = oraInizio;
        this.headphones=headphones;
        posizioni = new ArrayList<>();


    }

    public Visitatore(){}

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
        return OraFine;
    }

    public void setOraFine(Date oraFine) {
        OraFine = oraFine;
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

    public List<Posizione> getPosizioni() {
        return posizioni;
    }

    public void setPosizioni(List<Posizione> posizioni) {
        this.posizioni = posizioni;
    }
}
