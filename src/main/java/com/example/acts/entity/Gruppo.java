package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

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
    @ManyToOne
    private Stanza stanza;

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }

    public Gruppo(){}
    public Gruppo(Long id,Date data, Date oraInizio, Date oraFine, Boolean headphones,Stanza stanza) {
        this.id=id;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine=oraFine;
        this.headphones = headphones;
        this.stanza=stanza;
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
}
