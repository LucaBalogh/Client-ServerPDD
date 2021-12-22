package repo.database;

import model.Spectacol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.interfaces.ISpectacol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class SpectacolDBRepository implements ISpectacol {

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public SpectacolDBRepository(Properties props) {
        logger.info("Initializing ProbaDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public void add(Spectacol elem) {
        logger.traceEntry("saving task {}", elem);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("insert into spectacol (data_spectacol,title,pret_bilet,sala_id) values (?,?,?,?)")){
            preStmt.setString(1, elem.getData_spectacol().toString());
            preStmt.setString(2, elem.getTitlu());
            preStmt.setInt(3, elem.getPret_bilet());
            preStmt.setInt(4, elem.getSala());

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
    public List<Spectacol> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Spectacol> spectacole = new ArrayList<>();

        try(PreparedStatement preStmt = con.prepareStatement("select * from spectacol")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    LocalDate data_spectacol = LocalDate.parse(result.getString("data_spectacol"));
                    String titlu = result.getString("title");
                    int nr_locuri = result.getInt("nr_locuri");
                    int sala_id = result.getInt("sala_id");

                    Spectacol p = new Spectacol(data_spectacol, titlu, nr_locuri, sala_id);
                    p.setId(id);
                    spectacole.add(p);
                }
            }
        }
        catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit(spectacole);
        return spectacole;
    }


    @Override
    public List<Spectacol> findAllBySalaId(int id) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Spectacol> spectacole = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from spectacol where sala_id=?")){
            preStmt.setInt(1,id);
            ResultSet result=preStmt.executeQuery();
            System.out.println("findBySalaId  "+ "resOk");
            while(result.next()){
                LocalDate data_spectacol = LocalDate.parse(result.getString("data_spectacol"));
                String titlu = result.getString("title");
                int nr_locuri = result.getInt("nr_locuri");
                int sala_id = result.getInt("sala_id");
                Spectacol s = new Spectacol(data_spectacol, titlu, nr_locuri, sala_id);
                spectacole.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        logger.traceExit(spectacole);
        return spectacole;
    }
}
