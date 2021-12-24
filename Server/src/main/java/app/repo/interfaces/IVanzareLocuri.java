package app.repo.interfaces;

import app.model.VanzareLocuri;
import app.repo.Repository;

import java.util.List;

public interface IVanzareLocuri extends Repository<Integer, VanzareLocuri> {
    List<VanzareLocuri> findAllByVanzareId(int spectacolId);
}