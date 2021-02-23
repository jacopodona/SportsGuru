package com.example.myapplication.modules;

import java.io.Serializable;

public class Richiesta implements Serializable {

    private String nome,cognome,statistica,data;

    public Richiesta() {
        this.nome = "";
        this.cognome = "";
        this.statistica = "";
        this.data = "";
    }

    public Richiesta(String nome, String cognome, String statistica, String data) {
        this.nome = nome;
        this.cognome = cognome;
        this.statistica = statistica;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getStatistica() {
        return statistica;
    }

    public String getData() {
        return data;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setStatistica(String statistica) {
        this.statistica = statistica;
    }

    public void setData(String data) {
        this.data = data;
    }
}
