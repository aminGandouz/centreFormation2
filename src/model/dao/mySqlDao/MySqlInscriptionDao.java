package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Session;
import model.Stagiaire;
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
    public Boolean ajoutStagiaire(Stagiaire stagiaire, Session session) {
        Boolean ajoutOK = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String inscriptionExist = " select IdInscription from inscrire where IdUtilisateur = ? and IdSession = ? ";
        String sql = "INSERT INTO `inscrire` (`idUtilisateur`, `idSession`) values(?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(inscriptionExist);
            ps.setInt(1, stagiaire.getIdUtilisateur());
            ps.setInt(2, session.getIdSession());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, stagiaire.getIdUtilisateur());
                ps.setInt(2, session.getIdSession());
                ps.executeUpdate();
                ajoutOK = true;
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL ajoutStagiaire(Integer idUtilisateur, int session)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return ajoutOK;
    }
}
