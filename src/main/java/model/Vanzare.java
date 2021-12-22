package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vanzare extends Entity<Integer> {
    private LocalDate date_vanzare;

    private int spectacol_id;

    public Vanzare() {}

    public Vanzare(LocalDate date_vanzare, int spectacol_id) {
        this.date_vanzare = date_vanzare;
        this.spectacol_id = spectacol_id;
    }

    public LocalDate getDate_vanzare() {
        return date_vanzare;
    }

    public void setDate_vanzare(LocalDate date_vanzare) {
        this.date_vanzare = date_vanzare;
    }

    public int getSpectacol() {
        return spectacol_id;
    }

    public void setSpectacol(int spectacol_id) {
        this.spectacol_id = spectacol_id;
    }

    @Override
    public String toString() {
        return "Vanzare{" +
                "data='" + date_vanzare +
                "spectacolul='" + spectacol_id +
                '}';
    }
}
