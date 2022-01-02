package app.service;

import app.model.Sala;
import app.model.Spectacol;
import app.model.Vanzare;
import app.repo.interfaces.ISala;
import app.repo.interfaces.ISpectacol;
import app.repo.interfaces.IVanzare;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Service implements IService {
    private static final int NTHREADS = 1;
    private ExecutorService executor;


    private ISala salaRepo;
    private ISpectacol spectacolRepo;
    private IVanzare vanzareRepo;

    public Service(ISala salaRepo, ISpectacol spectacolRepo, IVanzare vanzareRepo) {
        this.salaRepo = salaRepo;
        this.spectacolRepo = spectacolRepo;
        this.vanzareRepo = vanzareRepo;

        try {
            FileWriter myWriter = new FileWriter("verificari.txt");
            myWriter.write("");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        this.executor = Executors.newFixedThreadPool(NTHREADS);

        Thread threadVerificare = new Thread(new Runnable() {
            @Override
            public void run() {
                Timer testare = new Timer();
                testare.scheduleAtFixedRate(
                        new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    verificare().get();
                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        5000,
                        10000
                );
            }
        });
        threadVerificare.start();
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
        try {
            vanzareAsync(v).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private Future<?> vanzareAsync(Vanzare v) {
        return executor.submit(() -> {
            if (getLocuriLibere(v.getSpectacol()).containsAll(v.getLocuri_vandute())) {
                vanzareRepo.add(v);
            }
        });

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

    private int getSoldSpectacol(int spectacol_id) {
        int sold = 0;
        Spectacol s = spectacolRepo.findOne(spectacol_id);
        List<Vanzare> vanzari = vanzareRepo.findAllBySpectacolId(spectacol_id);
        for (Vanzare v : vanzari) {
            sold += s.getPret_bilet() * v.getNr_locuri_vandute();
        }
        return sold;
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
        return getLocuriLibere(spectacol_id).size() == s.getNr_locuri() - locuriVandute.size();
    }

    private Future<?> verificare() {
        return (executor.submit(() -> {
            int ok = 0;
            for (Spectacol s : getAllSpectacol()) {
                if (checkLocuriLibere(s.getId()) && checkSoldTotal())
                    ok = 1;
                else
                    ok = 0;
            }
            if (ok == 1)
                System.out.println("Testarea a fost efectuata cu succes!");
            else
                System.out.println("Au fost probleme odata cu testarea!");
            try {
                FileWriter myWriter = new FileWriter("verificari.txt", true);
                myWriter.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n");
                for (var s : spectacolRepo.findAll()) {
                    myWriter.write(s + ", sold: " + getSoldSpectacol(s.getId()) + ", locuri vandute: " + s.getLocuri_vandute() + "\n");
                }
                if (ok == 1)
                    myWriter.write("Rezultat verificare: corect");
                else
                    myWriter.write("Rezultat verificare: incorect");
                myWriter.write("\n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}

