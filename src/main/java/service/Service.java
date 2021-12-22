package service;

import model.*;
import repo.interfaces.ISala;
import repo.interfaces.ISpectacol;
import repo.interfaces.IVanzareLocuri;
import repo.interfaces.IVanzare;

import java.util.List;

public class Service {

    private ISala salaRepo;
    private ISpectacol spectacolRepo;
    private IVanzare vanzareRepo;
    private IVanzareLocuri vanzareLocuriRepo;

    public Service(ISala salaRepo, ISpectacol spectacolRepo, IVanzare vanzareRepo, IVanzareLocuri vanzareLocuriRepo){
        this.salaRepo = salaRepo;
        this.spectacolRepo = spectacolRepo;
        this.vanzareRepo = vanzareRepo;
        this.vanzareLocuriRepo = vanzareLocuriRepo;
    }

    public List<Spectacol> getAllSpectacolBySalaId(int salaId){
        return spectacolRepo.findAllBySalaId(salaId);
    }

    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId){
        return vanzareRepo.findAllBySpectacolId(spectacolId);
    }

    public List<VanzareLocuri> getAllVanzareLocuriByVanzareId(int vanzareId){
        return vanzareLocuriRepo.findAllByVanzareId(vanzareId);
    }

    public List<Sala> getAllSala(){
        return salaRepo.findAll();
    }

    public List<Spectacol> getAllSpectacol(){
        return spectacolRepo.findAll();
    }

    public List<Vanzare> getAllVanzare(){
        return vanzareRepo.findAll();
    }

    public List<VanzareLocuri> getAllVanzareLocuri(){
        return vanzareLocuriRepo.findAll();
    }
}

