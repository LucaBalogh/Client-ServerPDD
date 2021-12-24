package app.server.service;

import app.server.model.Sala;
import app.server.model.Spectacol;
import app.server.model.Vanzare;
import app.server.model.VanzareLocuri;
import app.server.repo.interfaces.ISala;
import app.server.repo.interfaces.ISpectacol;
import app.server.repo.interfaces.IVanzare;
import app.server.repo.interfaces.IVanzareLocuri;

import java.util.List;

public class Service implements IService{

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

    @Override
    public List<Spectacol> getAllSpectacolBySalaId(int salaId){
        return spectacolRepo.findAllBySalaId(salaId);
    }

    @Override
    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId){
        return vanzareRepo.findAllBySpectacolId(spectacolId);
    }

    @Override
    public List<VanzareLocuri> getAllVanzareLocuriByVanzareId(int vanzareId){
        return vanzareLocuriRepo.findAllByVanzareId(vanzareId);
    }

    @Override
    public List<Sala> getAllSala(){
        return salaRepo.findAll();
    }

    @Override
    public List<Spectacol> getAllSpectacol(){
        return spectacolRepo.findAll();
    }

    @Override
    public List<Vanzare> getAllVanzare(){
        return vanzareRepo.findAll();
    }

    @Override
    public List<VanzareLocuri> getAllVanzareLocuri(){
        return vanzareLocuriRepo.findAll();
    }
}

