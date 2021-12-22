import model.Sala;
import repo.database.SalaDBRepository;
import repo.interfaces.ISala;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args){
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        ISala sRepo=new SalaDBRepository(props);

        List<Sala> s = sRepo.findAll();

        for(Sala ss : s)
            System.out.println(ss);

    }
}
