package com.example.acts.entity;

public class Mappa {
    private Long idStanza;
    private int TempoTotale;

    public Mappa (){}
    public Mappa(Long idStanza, int tempoTotale) {
        this.idStanza = idStanza;
        TempoTotale = tempoTotale;
    }

    public Long getIdStanza() {
        return idStanza;
    }

    public void setIdStanza(Long idStanza) {
        this.idStanza = idStanza;
    }

    public int getTempoTotale() {
        return TempoTotale;
    }

    public void setTempoTotale(int tempoTotale) {
        TempoTotale = tempoTotale;
    }
}
