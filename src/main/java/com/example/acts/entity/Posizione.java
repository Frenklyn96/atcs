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
    @ManyToOne
    private Gruppo gruppo;

    public Posizione(){}

    public Posizione(Stanza stanza, Date oraInizio, Date oraFine,Visitatore visitatore,Gruppo gruppo) {
        this.stanza = stanza;
        this.oraInizio = oraInizio;
        this.oraFine=oraFine;
        this.visitatore=visitatore;
        this.gruppo=gruppo;
    }

    public Gruppo getGruppo() {

        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
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

    public String getOraInizioString (){
        String a;
        if (String.valueOf(oraInizio.getMinutes()).length()==1)
            a= String.valueOf(oraInizio.getHours()+":0"+String.valueOf(oraInizio.getMinutes()));
        else
            a= String.valueOf(oraInizio.getHours()+":"+String.valueOf(oraInizio.getMinutes()));

        return(a);
    }
    public String getOraFineString (){
        String a;
        if (String.valueOf(oraFine.getMinutes()).length()==1)
             a= String.valueOf(oraFine.getHours()+":0"+String.valueOf(oraFine.getMinutes()));
        else
             a= String.valueOf(oraFine.getHours()+":"+String.valueOf(oraFine.getMinutes()));

        return(a);
    }
}
