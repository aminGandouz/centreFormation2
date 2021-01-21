package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Inscription;
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
        String prix = " SELECT distinct  `Prix` FROM `formation` join session on formation.IdFormation = session.IdFormation where session.IdSession = ? ";
        String reduction = " select reduction from status where status.idstatus = ? ";
        String sql = "INSERT INTO `inscrire` (`idUtilisateur`, `idSession`,`prix`) values(?,?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(inscriptionExist);
            ps.setInt(1, stagiaire.getIdUtilisateur());
            ps.setInt(2, session.getIdSession());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement(prix);
                ps.setInt(1, session.getIdSession());
                rs = ps.executeQuery();
                rs.next();
                Integer price = rs.getInt(1);

                ps = c.prepareStatement(reduction);
                ps.setInt(1, stagiaire.getStatus().getIdStatus());
                rs = ps.executeQuery();
                rs.next();
                Double reduc = rs.getDouble(1);

                float total = (float) (price - ((price / 100) * reduc));
                System.out.println(reduc);
                System.out.println(price);
                System.out.println(total);
                ps = c.prepareStatement(sql);
                ps.setInt(1, stagiaire.getIdUtilisateur());
                ps.setInt(2, session.getIdSession());
                ps.setFloat(3, total);
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

    @Override
    public void updateEstPaye(Inscription inscription) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  UPDATE `inscrire` SET `EstPaye`= ? WHERE  inscrire.IdInscription = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, inscription.getIdInscription());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL updateEstPaye(Inscription inscription)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }
}
