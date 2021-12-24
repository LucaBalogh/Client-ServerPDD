package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartServer {
    public static void main(String[] args){
        //long start = System.currentTimeMillis();
        //long end = start + 120 * 1000;
        //while (System.currentTimeMillis() < end) {
            try {
                Registry reg = LocateRegistry.createRegistry(1099);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
        //}

    }
}
