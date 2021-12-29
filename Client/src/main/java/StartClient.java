import app.model.Spectacol;
import app.model.Vanzare;
import app.service.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.*;

public class StartClient {

    public static int generateTicketNumber(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void main(String[] args){
        try{
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IService server=(IService)factory.getBean("appService");
            System.out.println("S-a facut conexiunea la server!");
            List<Spectacol> s = server.getAllSpectacol();

            for(Spectacol ss : s)
                System.out.println(ss);
            System.out.println(server.getSoldTotal());
            System.out.println(server.checkSoldTotal());
            System.out.println(server.getLocuriLibere(1));
            System.out.println(server.checkLocuriLibere(1));

            Timer cumparare = new Timer();
            cumparare.scheduleAtFixedRate(
                new TimerTask() {
                    //int vanzare = server.getAllVanzare().get(server.getAllVanzare().size() - 1).getId();
                    //int nrBilete = generateTicketNumber(1, 15);
                    //int spectacol_id = generateTicketNumber(1, 3);
                    @Override
                    public void run() {
                        int spectacol_id = generateTicketNumber(1, 3);
                        int min = 15;
                        List<Integer> locuriLibere = server.getLocuriLibere(spectacol_id);
                        if(locuriLibere.size()<min) min = locuriLibere.size();
                        int nrBilete = generateTicketNumber(1, min);
                        Collections.shuffle(locuriLibere);
                        List<Integer> locuriVandute = new ArrayList<>(locuriLibere.subList(0,nrBilete));
                        Vanzare v = new Vanzare(LocalDate.now(), spectacol_id,locuriVandute);
                        //VanzareLocuri vL = new VanzareLocuri(nrBilete, vanzare);
                        try{  //if(server.getLocuriLibere(spectacol_id) >= vL.getNr_locuri())
                            server.addVanzare(v);
                            //server.addVanzareLocuri(vL);
                            System.out.println("Vanzarea a fost realizata cu succes!");;
                        }
                        catch (Exception ex) {
                            System.out.println("Vanzarea nu a putut fi realizata!");
                        }

                        if(server.getLocuriLibere(spectacol_id).size() == 0)
                            cumparare.cancel();
                    }
                },
                5000,
                2000
            );

            Timer testare = new Timer();
            testare.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        int ok = 0;
                        for(Spectacol s : server.getAllSpectacol()){
                            if(server.checkLocuriLibere(s.getId()) && server.checkSoldTotal())
                                ok = 1;
                            else
                                ok = 0;
                        }
                        if(ok == 1)
                            System.out.println("Testarea a fost efectuata cu succes!");
                        else
                            System.out.println("Au fost probleme odata cu testarea!");
                    }
                },
                5000,
                10000
            );
        }catch (Exception e){
            System.out.println("Server ul nu este activ!");
        }
    }
}
