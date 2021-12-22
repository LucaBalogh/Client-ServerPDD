package repo.interfaces;

import model.VanzareLocuri;
import repo.Repository;

import java.util.List;

public interface IVanzareLocuri extends Repository<Integer, VanzareLocuri> {
    List<VanzareLocuri> findAllByVanzareId(int spectacolId);
}