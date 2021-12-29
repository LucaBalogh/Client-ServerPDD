package app.repo.interfaces;

import app.model.Spectacol;
import app.repo.Repository;

import java.util.List;

public interface ISpectacol extends Repository<Integer, Spectacol> {
    List<Spectacol> findAllBySalaId(int salaId);
    Spectacol findOne(int id);
}