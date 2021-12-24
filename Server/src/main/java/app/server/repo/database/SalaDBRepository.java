package app.server.repo.database;
import app.server.model.Sala;
import app.server.repo.interfaces.ISala;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class SalaDBRepository implements ISala {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public SalaDBRepository(Properties props) {
        logger.info("Initializing SalaDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

//    @Override
//    public List<Inscriere> findAllByProba(String p) {
//        logger.traceEntry();
//        Connection con = dbUtils.getConnection();
//        List<Inscriere> inss = new ArrayList<>();
//        try(PreparedStatement preStmt=con.prepareStatement("select Participant,Proba from Inscriere where Proba=?")){
//            preStmt.setString(1,p);
//            ResultSet result=preStmt.executeQuery();
//            System.out.println("findByProba proba "+ "resOk");
//            while(result.next()){
//                String participant = result.getString("Participant");
//                String proba = result.getString("Proba");
//                Inscriere ins = new Inscriere(participant,proba);
//                inss.add(ins);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error DB "+e);
//        }
//        logger.traceExit(inss);
//        return inss;
//    }

    @Override
    public void add(Sala elem) {
        logger.traceEntry("saving task {}", elem);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("insert into sala (nr_locuri) values (?)")){
            preStmt.setInt(1, elem.getNr_locuri());;

            int result = preStmt.executeUpdate();

            logger.trace("Saved {} instance",result);
        }
        catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB" + ex);
        }
        logger.traceExit();
    }

    @Override
    public List<Sala> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Sala> sala = new ArrayList<>();

        try(PreparedStatement preStmt = con.prepareStatement("select * from sala")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int nr_locuri = result.getInt("nr_locuri");

                    Sala s = new Sala(nr_locuri);
                    s.setId(id);
                    sala.add(s);
                }
            }
        }
        catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit(sala);
        return sala;
    }

//    @Override
//    public Participant findBy(String username, String pass) {
//        System.out.println("JDBC findBy 2 params");
//        Connection con = dbUtils.getConnection();
//        try(PreparedStatement preStmt=con.prepareStatement("select name from Participant where name=? and passwd=?")){
//            preStmt.setString(1,username);
//            preStmt.setString(2,pass);
//            ResultSet result=preStmt.executeQuery();
//            boolean resOk=result.next();
//            System.out.println("findBy user, pass "+resOk);
//            if (resOk) {
//                Participant user=new Participant(username,0,"");
//                user.setName(result.getString("name"));
//                return user;
//            }
//        } catch (SQLException e) {
//            System.out.println("Error DB "+e);
//        }
//        return null;
//    }
}
