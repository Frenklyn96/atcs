package com.example.acts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Posizione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne
    private Stanza stanza;

    @NotNull
    private Date oraInizio;

    @NotNull
    private Date oraFine;
    @ManyToOne
    private Visitatore visitatore;

    public Posizione(){}

    public Posizione(Stanza stanza, Date oraInizio, Date oraFine,Visitatore visitatore) {
        this.stanza = stanza;
        this.oraInizio = oraInizio;
        this.oraFine=oraFine;
        this.visitatore=visitatore;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
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
