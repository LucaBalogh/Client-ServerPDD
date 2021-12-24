import app.server.model.Sala;
import app.server.service.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StartClient {
    public static void main(String[] args){
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IService server=(IService)factory.getBean("appService");
        System.out.println("S-a facut conexiunea la server!");
        List<Sala> s = server.getAllSala();

        for(Sala ss : s)
            System.out.println(ss);
    }
}
