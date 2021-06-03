package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Presentazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    public String nome;

    @NotNull
    private Date oraInizio;

    @NotNull
    private Date oraFine;
    @ManyToOne
    private Visitatore visitatore;
    @NotNull
    private int voto;
    @NotBlank
    private String interruzione;


    public Presentazione(){}

    public Presentazione(String nome, Date oraInizio, Date oraFine,Visitatore visitatore, int voto, String interruzione) {
        this.nome = nome;
        this.oraInizio = oraInizio;
        this.oraFine=oraFine;
        this.visitatore=visitatore;
        this.voto=voto;
        this.interruzione=interruzione;
    }



    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getInterruzione() {
        return interruzione;
    }

    public void setInterruzione(String interruzione) {
        this.interruzione = interruzione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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

    public Visitatore getVisitatore() {
        return visitatore;
    }

    public void setVisitatore(Visitatore visitatore) {
        this.visitatore = visitatore;
    }
}
