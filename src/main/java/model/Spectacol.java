package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Spectacol extends Entity<Integer> {

    private LocalDate data_spectacol;

    private String titlu;

    private int pret_bilet;

    private int sala_id;


    public Spectacol() {}

    public Spectacol(LocalDate data_spectacol, String titlu, int pret_bilet,  int sala_id) {
        this.data_spectacol = data_spectacol;
        this.titlu = titlu;
        this.pret_bilet = pret_bilet;
        this.sala_id = sala_id;
    }

    public LocalDate getData_spectacol() {
        return data_spectacol;
    }

    public void setData_spectacol(LocalDate data_spectacol) {
        this.data_spectacol = data_spectacol;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getPret_bilet() {
        return pret_bilet;
    }

    public void setPret_bilet(int pret_bilet) {
        this.pret_bilet = pret_bilet;
    }

    public int getSala() {
        return sala_id;
    }

    public void setSala(int sala) {
        this.sala_id = sala;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "titlu='" + titlu  +
                ", startDate=" + data_spectacol +
                ", pretbilet=" + pret_bilet +
                ", sala=" + sala_id +
                '}';
    }

}

