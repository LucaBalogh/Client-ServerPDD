package app.server.repo.interfaces;

import app.server.model.Spectacol;
import app.server.repo.Repository;

import java.util.List;

public interface ISpectacol extends Repository<Integer, Spectacol> {
    List<Spectacol> findAllBySalaId(int salaId);
}