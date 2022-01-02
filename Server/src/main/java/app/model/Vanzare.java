package app.model;

import java.time.LocalDate;
import java.util.List;

public class Vanzare extends Entity {
    private LocalDate date_vanzare;

    private int spectacol_id;

    private int nr_locuri_vandute;

    private List<Integer> locuri_vandute;

    public Vanzare() {}

    public Vanzare(LocalDate date_vanzare, int spectacol_id,List<Integer> locuri_vandute) {
        this.date_vanzare = date_vanzare;
        this.spectacol_id = spectacol_id;
        this.locuri_vandute = locuri_vandute;
        this.nr_locuri_vandute = locuri_vandute.size();
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

    public int getNr_locuri_vandute() {
        return nr_locuri_vandute;
    }

    public List<Integer> getLocuri_vandute() {
        return locuri_vandute;
    }

    public void setLocuri_vandute(List<Integer> locuri_vandute) {
        this.locuri_vandute = locuri_vandute;
        this.nr_locuri_vandute = locuri_vandute.size();
    }

    @Override
    public String toString() {
        return "Vanzare{" +
                "data='" + date_vanzare +
                "spectacolul='" + spectacol_id +
                "locuri vandute= "+ locuri_vandute+
                '}';
    }
}
