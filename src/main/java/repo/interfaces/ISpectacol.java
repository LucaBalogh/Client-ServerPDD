package repo.interfaces;

import model.Spectacol;
import model.Vanzare;
import repo.Repository;

import java.util.List;

public interface ISpectacol extends Repository<Integer, Spectacol> {
    List<Spectacol> findAllBySalaId(int salaId);
}