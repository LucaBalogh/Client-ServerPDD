package app;

import app.model.Spectacol;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.NoSuchObjectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

public class StartServer {
    public static void main(String[] args){
            try {
                Registry reg = LocateRegistry.createRegistry(1099);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");

        Timer run = new Timer();
        run.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        ((ConfigurableApplicationContext)factory).close();
                    }
                },
                120000
        );

    }
}
