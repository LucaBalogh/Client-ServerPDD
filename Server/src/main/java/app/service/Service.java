package app.service;

import app.model.Sala;
import app.model.Spectacol;
import app.model.Vanzare;
import app.model.VanzareLocuri;
import app.repo.interfaces.ISala;
import app.repo.interfaces.ISpectacol;
import app.repo.interfaces.IVanzare;
import app.repo.interfaces.IVanzareLocuri;

import java.util.ArrayList;
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

    @Override
    public void addVanzare(Vanzare v){
        vanzareRepo.add(v);
    }

    @Override
    public void addVanzareLocuri(VanzareLocuri vL){
        vanzareLocuriRepo.add(vL);
    }

    @Override
    public int getSoldTotal(){
        List<Vanzare> vanzari;
        List<VanzareLocuri> vanzariLocuri;
        int sold = 0;
        int bilete = 0;
        for(Spectacol s : spectacolRepo.findAll()){
            vanzari = vanzareRepo.findAllBySpectacolId(s.getId());
            for(Vanzare v : vanzari){
                vanzariLocuri = vanzareLocuriRepo.findAllByVanzareId(v.getId());
                for(VanzareLocuri vL : vanzariLocuri)
                    bilete += vL.getNr_locuri();
            }
            sold += s.getPret_bilet() * bilete;
            bilete = 0;
        }
        return sold;
    }

    @Override
    public boolean checkSoldTotal(){
        List<Vanzare> vanzari;
        List<VanzareLocuri> vanzariLocuri;
        int sold = 0;
        int bilete = 0;
        for(Spectacol s : spectacolRepo.findAll()){
            vanzari = vanzareRepo.findAllBySpectacolId(s.getId());
            for(Vanzare v : vanzari){
                vanzariLocuri = vanzareLocuriRepo.findAllByVanzareId(v.getId());
                for(VanzareLocuri vL : vanzariLocuri)
                    bilete += vL.getNr_locuri();
            }
            sold += s.getPret_bilet() * bilete;
            bilete = 0;
        }
        return getSoldTotal() == sold;
    }

    @Override
    public int getLocuriLibere(int spectacol_id){
        Sala s = salaRepo.findAll().get(0);
        int nr_locuri = 0;
        List<Vanzare> vanzari = vanzareRepo.findAllBySpectacolId(spectacol_id);
        List<VanzareLocuri> vanzareL;
        for(Vanzare v : vanzari){
            vanzareL = vanzareLocuriRepo.findAllByVanzareId(v.getId());
            for(VanzareLocuri vv : vanzareL)
                nr_locuri += vv.getNr_locuri();
        }
        return s.getNr_locuri() - nr_locuri;
    }

    @Override
    public boolean checkLocuriLibere(int spectacol_id){
        Sala s = salaRepo.findAll().get(0);
        int nr_locuri = 0;
        List<Vanzare> vanzari = vanzareRepo.findAllBySpectacolId(spectacol_id);
        List<VanzareLocuri> vanzareL;
        for(Vanzare v : vanzari){
            vanzareL = vanzareLocuriRepo.findAllByVanzareId(v.getId());
            for(VanzareLocuri vv : vanzareL)
                nr_locuri += vv.getNr_locuri();
        }
        return getLocuriLibere(spectacol_id) == s.getNr_locuri() - nr_locuri;
    }
}

