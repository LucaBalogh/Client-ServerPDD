package app.service;

import app.model.Sala;
import app.model.Spectacol;
import app.model.Vanzare;
import app.model.VanzareLocuri;

import java.util.List;

public interface IService {
    public List<Spectacol> getAllSpectacolBySalaId(int salaId);

    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId);

    public List<VanzareLocuri> getAllVanzareLocuriByVanzareId(int vanzareId);

    public List<Sala> getAllSala();

    public List<Spectacol> getAllSpectacol();

    public List<Vanzare> getAllVanzare();

    public List<VanzareLocuri> getAllVanzareLocuri();

    public int getSoldTotal();

    public int getLocuriLibere(int spectacol_id);

    public boolean checkLocuriLibere(int spectacol_id);

    public void addVanzare(Vanzare v);

    public void addVanzareLocuri(VanzareLocuri vL);

    public boolean checkSoldTotal();
}
