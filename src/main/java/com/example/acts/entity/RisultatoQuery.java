package com.example.acts.entity;

import java.util.Date;

public class RisultatoQuery {
    private Long id;
    private Date oraInizio;
    private Date oraFine;
    private Gruppo gruppo;
    private Stanza stanza;

    public RisultatoQuery(Long id, Date oraInizio, Date oraFine, Gruppo gruppo, Stanza stanza) {
        this.id = id;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.gruppo = gruppo;
        this.stanza = stanza;
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
        this.oraFine = oraFine;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
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