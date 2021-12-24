package app.server.repo.interfaces;

import app.server.model.VanzareLocuri;
import app.server.repo.Repository;

import java.util.List;

public interface IVanzareLocuri extends Repository<Integer, VanzareLocuri> {
    List<VanzareLocuri> findAllByVanzareId(int spectacolId);
}