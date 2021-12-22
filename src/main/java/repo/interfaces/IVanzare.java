package repo.interfaces;

import model.Vanzare;
import repo.Repository;

import java.util.List;

public interface IVanzare extends Repository<Integer, Vanzare> {
    List<Vanzare> findAllBySpectacolId(int spectacolId);
}