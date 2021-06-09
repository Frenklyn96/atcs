package com.example.acts.entity;

public class Mappa {
    private Long id;
    private int time;

    public Mappa (){}
    public Mappa(Long idStanza, int tempoTotale) {
        this.id = idStanza;
        this.time = tempoTotale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idStanza) {
        this.id = idStanza;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int tempoTotale) {
        time = tempoTotale;
    }
}
