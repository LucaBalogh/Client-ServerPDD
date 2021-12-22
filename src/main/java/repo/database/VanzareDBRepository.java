package repo.database;

import model.Vanzare;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.interfaces.IVanzare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class VanzareDBRepository implements IVanzare {

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public VanzareDBRepository(Properties props) {
        logger.info("Initializing VanzareDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public void add(Vanzare elem) {
        logger.traceEntry("saving task {}", elem);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("insert into vanzare (date_vanzare,spectacol_id) values (?,?)")){
            preStmt.setString(1, elem.getDate_vanzare().toString());
            preStmt.setInt(2, elem.getSpectacol());

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
    public List<Vanzare> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Vanzare> vanzari = new ArrayList<>();

        try(PreparedStatement preStmt = con.prepareStatement("select * from vanzare")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    LocalDate data_vanzare = LocalDate.parse(result.getString("data_vanzare"));
                    int spectacol_id = result.getInt("spectacol_id");

                    Vanzare v = new Vanzare(data_vanzare, spectacol_id);
                    v.setId(id);
                    vanzari.add(v);
                }
            }
        }
        catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit(vanzari);
        return vanzari;
    }

    @Override
    public List<Vanzare> findAllBySpectacolId(int id) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Vanzare> vanzari = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from vanzare where spectacol_id=?")){
            preStmt.setInt(1,id);
            ResultSet result=preStmt.executeQuery();
            System.out.println("findBySpecatcolId  "+ "resOk");
            while(result.next()){
                LocalDate date_vanzare = LocalDate.parse(result.getString("date_vanzare"));
                int spectacol_id = result.getInt("spectacol_id");
                Vanzare ins = new Vanzare(date_vanzare,spectacol_id);
                vanzari.add(ins);
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        logger.traceExit(vanzari);
        return vanzari;
    }

}
