package app.service;

import app.model.Sala;
import app.model.Spectacol;
import app.model.Vanzare;
import app.repo.interfaces.ISala;
import app.repo.interfaces.ISpectacol;
import app.repo.interfaces.IVanzare;

import java.util.ArrayList;
import java.util.List;

public class Service implements IService {
    private static final int NTHREADS = 5;

    private ISala salaRepo;
    private ISpectacol spectacolRepo;
    private IVanzare vanzareRepo;

    public Service(ISala salaRepo, ISpectacol spectacolRepo, IVanzare vanzareRepo) {
        this.salaRepo = salaRepo;
        this.spectacolRepo = spectacolRepo;
        this.vanzareRepo = vanzareRepo;
    }

    @Override
    public List<Spectacol> getAllSpectacolBySalaId(int salaId) {
        return spectacolRepo.findAllBySalaId(salaId);
    }

    @Override
    public List<Vanzare> getAllVanzareBySpectacolId(int spectacolId) {
        return vanzareRepo.findAllBySpectacolId(spectacolId);
    }

    @Override
    public List<Sala> getAllSala() {
        return salaRepo.findAll();
    }

    @Override
    public List<Spectacol> getAllSpectacol() {
        return spectacolRepo.findAll();
    }

    @Override
    public List<Vanzare> getAllVanzare() {
        return vanzareRepo.findAll();
    }

    @Override
    public void addVanzare(Vanzare v) {
        vanzareRepo.add(v);
    }

    @Override
    public int getSoldTotal() {
        List<Vanzare> vanzari;
        int sold = 0;
        int bilete = 0;
        for (Spectacol s : spectacolRepo.findAll()) {
            vanzari = vanzareRepo.findAllBySpectacolId(s.getId());
            for (Vanzare v : vanzari) {
                bilete += v.getNr_locuri_vandute();
            }
            sold += s.getPret_bilet() * bilete;
            bilete = 0;
        }
        return sold;
    }

    @Override
    public boolean checkSoldTotal() {
        List<Vanzare> vanzari;
        int sold = 0;
        int bilete = 0;
        for (Spectacol s : spectacolRepo.findAll()) {
            vanzari = vanzareRepo.findAllBySpectacolId(s.getId());
            for (Vanzare v : vanzari) {
                bilete += v.getNr_locuri_vandute();
            }
            sold += s.getPret_bilet() * bilete;
            bilete = 0;
        }
        return getSoldTotal() == sold;
    }

    @Override
    public List<Integer> getLocuriLibere(int spectacol_id) {
        Sala s = salaRepo.findAll().get(0);
        List<Vanzare> vanzari = vanzareRepo.findAllBySpectacolId(spectacol_id);
        List<Integer> locuriVandute = new ArrayList<>();
        List<Integer> locuriLibere = new ArrayList<>();
        for (Vanzare v : vanzari) {
            locuriVandute.addAll(v.getLocuri_vandute());
        }
        for (int i = 1; i <= s.getNr_locuri(); i++)
            if (!locuriVandute.contains(i))
                locuriLibere.add(i);
        return locuriLibere;
    }

    @Override
    public boolean checkLocuriLibere(int spectacol_id) {
        Sala s = salaRepo.findAll().get(0);
        List<Vanzare> vanzari = vanzareRepo.findAllBySpectacolId(spectacol_id);
        List<Integer> locuriVandute = new ArrayList<>();
        for (Vanzare v : vanzari) {
            locuriVandute.addAll(v.getLocuri_vandute());
        }
        return getLocuriLibere(spectacol_id).size() == s.getNr_locuri() - locuriVandute.size();  //nr_locuri
    }
}

