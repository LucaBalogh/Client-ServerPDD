package app.server.service;

import app.server.model.Sala;
import app.server.model.Spectacol;
import app.server.model.Vanzare;
import app.server.model.VanzareLocuri;

import java.util.List;

public interface IService {
    public List<Spectacol> getAllSpectacolBySalaId(int salaId);

    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId);

    public List<VanzareLocuri> getAllVanzareLocuriByVanzareId(int vanzareId);

    public List<Sala> getAllSala();

    public List<Spectacol> getAllSpectacol();

    public List<Vanzare> getAllVanzare();

    public List<VanzareLocuri> getAllVanzareLocuri();
}
