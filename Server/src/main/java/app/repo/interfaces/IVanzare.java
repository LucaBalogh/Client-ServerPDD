package app.repo.interfaces;

import app.model.Vanzare;
import app.repo.Repository;

import java.util.List;

public interface IVanzare extends Repository<Integer, Vanzare> {
    List<Vanzare> findAllBySpectacolId(int spectacolId);
}