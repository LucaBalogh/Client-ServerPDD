import app.model.Spectacol;
import app.model.Vanzare;
import app.service.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.*;

public class StartClient {

    public static int generateTicketNumber(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static void main(String[] args){
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IService server = (IService) factory.getBean("appService");
            System.out.println("S-a facut conexiunea la server!");
            List<Spectacol> s = server.getAllSpectacol();

            for (Spectacol ss : s)
                System.out.println(ss);
            System.out.println(server.getSoldTotal());
            System.out.println(server.checkSoldTotal());
            System.out.println(server.getLocuriLibere(1));
            System.out.println(server.checkLocuriLibere(1));

            Timer cumparare = new Timer();
            cumparare.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                int spectacol_id = generateTicketNumber(1, server.getAllSpectacol().size());
                                int min = 15;
                                List<Integer> locuriLibere = server.getLocuriLibere(spectacol_id);
                                if (locuriLibere.size() != 0) {
                                    if (locuriLibere.size() < min) min = locuriLibere.size();
                                    int nrBilete = generateTicketNumber(1, min);
                                    Collections.shuffle(locuriLibere);
                                    List<Integer> locuriVandute = new ArrayList<>(locuriLibere.subList(0, nrBilete));
                                    Vanzare v = new Vanzare(LocalDate.now(), spectacol_id, locuriVandute);
                                    try {
                                        server.addVanzare(v);
                                        System.out.println("Vanzarea a fost realizata cu succes!");
                                    } catch (Exception ex) {
                                        System.out.println("Vanzarea nu a putut fi realizata!");
                                    }
                                }
                                boolean liber = false;
                                for (Spectacol s : server.getAllSpectacol())
                                    if (server.getLocuriLibere(s.getId()).size() > 0) {
                                        liber = true;
                                        break;
                                    }
                                if (!liber)
                                    cumparare.cancel();
                            }
                            catch (RemoteException ex) {
                                System.out.println("Server ul nu mai este activ!");
                                cumparare.cancel();
                            }
                        }
                    },
                    5000,
                    2000
            );
        } catch (Exception e) {
            System.out.println("Server ul nu mai este activ!");
        }
    }
}
