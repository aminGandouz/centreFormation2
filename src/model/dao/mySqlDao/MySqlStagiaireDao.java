package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Inscription;
import model.Stagiaire;
import model.dao.StagiaireDao;

public class MySqlStagiaireDao implements StagiaireDao {

    private static MySqlStagiaireDao instance;

    static {
        instance = new MySqlStagiaireDao();
    }

    public static MySqlStagiaireDao getInstance() {
        return instance;
    }

    private MySqlStagiaireDao() {
    }

    @Override
    public void addStagiaire(Stagiaire stagiaire) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into utilisateur(nom,prenom,adresse,telephone,email,login,password,role,status,Enable) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, stagiaire.getNom());
            ps.setString(2, stagiaire.getPrenom());
            ps.setString(3, stagiaire.getAdresse());
            ps.setString(4, stagiaire.getTelephone());
            ps.setString(5, stagiaire.getEmail());
            ps.setString(6, stagiaire.getLogin());
            ps.setString(7, stagiaire.getPassword());
            ps.setInt(8, 2);
            ps.setInt(9, stagiaire.getStatus().getIdStatus());
            ps.setBoolean(10, true);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL addStagiaire(Stagiaire stagiaire)");
            e.printStackTrace();
            
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }

    }

    @Override
    public void updateStagiaire(Stagiaire stagiaire) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update utilisateur set Nom = ?,Prenom = ?,"
                + "Adresse = ?,Telephone = ?,Email = ?,"
                + " Login = ?,Password = ?,"
                + "Role = ?,Status = ? where idUtilisateur = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, stagiaire.getNom());
            ps.setString(2, stagiaire.getPrenom());
            ps.setString(3, stagiaire.getAdresse());
            ps.setString(4, stagiaire.getTelephone());
            ps.setString(5, stagiaire.getEmail());
            ps.setString(6, stagiaire.getLogin());
            ps.setString(7, stagiaire.getPassword());
            ps.setInt(8, 2);
            ps.setInt(9, stagiaire.getStatus().getIdStatus());
            ps.setInt(10, stagiaire.getIdUtilisateur());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL update du stagiaire ");
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public List<Inscription> getListDesInscriptions(Stagiaire stagiaire) {
        List<Inscription> listInscription = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdInscription`, `IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`, `prix` FROM `inscrire` WHERE idUtilisateur = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUtilisateur());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscritpion = new Inscription(rs.getInt(1), rs.getBoolean(4), rs.getBoolean(5), rs.getFloat(6));
                listInscription.add(inscritpion);
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getListDesInscriptions(Integer idUtilisateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listInscription;
    }

    @Override
    public Inscription getInscritpionById(int choixInscription) {
        Inscription inscription = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdInscription`, `IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`, `prix` FROM `inscrire` WHERE IdInscription = ?  ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, choixInscription);
            rs = ps.executeQuery();
            if (rs.next()) {
                inscription = new Inscription(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getFloat(4));
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getInscritpionById(int choixInscription)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return inscription;
    }

    @Override
    public Boolean annulerInscription(Inscription inscription) {
        Boolean ok = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " delete FROM `inscrire` WHERE IdInscription = ?  ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, inscription.getIdInscription());
            ps.executeUpdate();
            ok = true;
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getInscritpionById(int choixInscription)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return ok;
    }

    @Override
    public List<Inscription> getListInscriptionNonPayer(Stagiaire stagiaire) {
        List<Inscription> listInscription = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdInscription`, `IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`, `prix` FROM `inscrire` WHERE idUtilisateur = ? and signalisation = ? and EstPaye = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUtilisateur());
            ps.setBoolean(2, false);
            ps.setBoolean(3, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscritpion = new Inscription(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getFloat(4));
                listInscription.add(inscritpion);
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getListDesInscriptions(Integer idUtilisateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listInscription;
    }

    @Override
    public Boolean signalerPaiement(Inscription inscription) {
        Boolean ok = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " UPDATE `inscrire` SET `Signalisation`= ? WHERE IdInscription = ?  ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, inscription.getIdInscription());
            ps.executeUpdate();
            ok = true;
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getInscritpionById(int choixInscription)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return ok;
    }
}
