package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Gruppo {
    @Id
    private Long id;
    @NotNull
    private Date data;
    @NotNull
    private Date oraInizio;
    @NotNull
    private Date oraFine;
    @NotNull
    private Boolean headphones;
    @ManyToMany
    @JoinTable(name="gruppo_stanze",
    joinColumns = @JoinColumn(name="gruppo_id"),
    inverseJoinColumns = @JoinColumn(name="stanza_id"))
    private Set<Stanza> stanze;
    @OneToMany
    private Set<Visitatore> visitatori;
    @OneToMany
    private Set<Posizione> posizioni;



    public Gruppo(){}
    public Gruppo(Long id,Date data, Date oraInizio, Date oraFine, Boolean headphones) {
        this.id=id;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine=oraFine;
        this.headphones = headphones;
        this.stanze=new HashSet<Stanza>();
        this.visitatori= new HashSet<Visitatore>();
        this.posizioni=new HashSet<Posizione>();
    }

    public void addPosizione (Posizione p){
        posizioni.add(p);
    }
    public Set<Posizione> getPosizione() {
        return posizioni;
    }

    public void setPosizione(Set<Posizione> posizioni) {
        this.posizioni = posizioni;
    }

    public Set<Visitatore> getVisitatori() {
        return visitatori;
    }

    public void setVisitatori(Set<Visitatore> visitatori) {
        this.visitatori = visitatori;
    }

    public Set<Stanza> getStanze() {
        return stanze;
    }

    public void setStanze(Set<Stanza> stanze) {
        this.stanze = stanze;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        this.oraFine = oraFine;
    }

    public Boolean getHeadphones() {
        return headphones;
    }

    public void setHeadphones(Boolean headphones) {
        this.headphones = headphones;
    }

    public void addStanza(Stanza stanza) {
        stanze.add(stanza);
    }

    public void addVisitatore(Visitatore v) {
        visitatori.add(v);
    }
}
