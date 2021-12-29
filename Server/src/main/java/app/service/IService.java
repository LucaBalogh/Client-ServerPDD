package app.service;

import app.model.Sala;
import app.model.Spectacol;
import app.model.Vanzare;

import java.util.List;

public interface IService {
    public List<Spectacol> getAllSpectacolBySalaId(int salaId);

    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId);


    public List<Sala> getAllSala();

    public List<Spectacol> getAllSpectacol();

    public List<Vanzare> getAllVanzare();

    public int getSoldTotal();

    public List<Integer> getLocuriLibere(int spectacol_id);

    public boolean checkLocuriLibere(int spectacol_id);

    public void addVanzare(Vanzare v);

    public boolean checkSoldTotal();

}
