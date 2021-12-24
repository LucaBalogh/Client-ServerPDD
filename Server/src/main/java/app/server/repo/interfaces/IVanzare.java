package app.server.repo.interfaces;

import app.server.model.Vanzare;
import app.server.repo.Repository;

import java.util.List;

public interface IVanzare extends Repository<Integer, Vanzare> {
    List<Vanzare> findAllBySpectacolId(int spectacolId);
}