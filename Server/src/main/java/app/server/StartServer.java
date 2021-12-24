package app.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartServer {
    public static void main(String[] args){
        /*Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        ISala sRepo=new SalaDBRepository(props);

        List<Sala> s = sRepo.findAll();

        for(Sala ss : s)
            System.out.println(ss);*/
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");

    }
}
