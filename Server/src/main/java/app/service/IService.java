package app.service;

import app.model.Sala;
import app.model.Spectacol;
import app.model.Vanzare;

import java.rmi.RemoteException;
import java.util.List;

public interface IService {
    public List<Spectacol> getAllSpectacolBySalaId(int salaId) throws RemoteException;

    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId) throws RemoteException;


    public List<Sala> getAllSala() throws RemoteException;

    public List<Spectacol> getAllSpectacol() throws RemoteException;

    public List<Vanzare> getAllVanzare() throws RemoteException;

    public int getSoldTotal() throws RemoteException;

    public List<Integer> getLocuriLibere(int spectacol_id) throws RemoteException;

    public boolean checkLocuriLibere(int spectacol_id) throws RemoteException;

    public void addVanzare(Vanzare v) throws RemoteException;

    public boolean checkSoldTotal() throws RemoteException;

}
