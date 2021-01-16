package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.InscriptionDao;

public class MySqlInscriptionDao implements InscriptionDao {

    private static MySqlInscriptionDao instance;

    static {
        instance = new MySqlInscriptionDao();
    }

    public static MySqlInscriptionDao getInstance() {
        return instance;
    }

    private MySqlInscriptionDao() {
    }

    @Override
    public void ajoutStagiaire(Integer idUtilisateur,Integer session) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `inscrire` (`idUtilisateur`, `idSession`) values(?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idUtilisateur);
            ps.setInt(2, session);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL ajoutStagiaire(Integer idUtilisateur, int session)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }
}
