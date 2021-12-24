package app.repo.database;

import app.model.VanzareLocuri;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import app.repo.interfaces.IVanzareLocuri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class VanzareLocuriDBRepository implements IVanzareLocuri {

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public VanzareLocuriDBRepository(Properties props) {
        logger.info("Initializing VanzareLocuriDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public void add(VanzareLocuri elem) {
        logger.traceEntry("saving task {}", elem);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("insert into vanzarelocuri (nr_locuri,vanzare_id) values (?,?)")){
            preStmt.setInt(1, elem.getNr_locuri());
            preStmt.setInt(2, elem.getVanzare_id());

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
    public List<VanzareLocuri> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<VanzareLocuri> vanzari = new ArrayList<>();

        try(PreparedStatement preStmt = con.prepareStatement("select * from vanzarelocuri")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int nr_locuri = result.getInt("nr_locuri");
                    int vanzare_id = result.getInt("vanzare_id");

                    VanzareLocuri v = new VanzareLocuri(nr_locuri, vanzare_id);
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
    public List<VanzareLocuri> findAllByVanzareId(int id) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<VanzareLocuri> vanzari = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from vanzarelocuri where vanzare_id=?")){
            preStmt.setInt(1,id);
            ResultSet result=preStmt.executeQuery();
            System.out.println("findByVanzareId  "+ "resOk");
            while(result.next()){
                int idd = result.getInt("id");
                int nr_locuri = result.getInt("nr_locuri");
                int vanzare_id = result.getInt("vanzare_id");
                VanzareLocuri ins = new VanzareLocuri(nr_locuri,vanzare_id);
                ins.setId(idd);
                vanzari.add(ins);
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        logger.traceExit(vanzari);
        return vanzari;
    }

}
