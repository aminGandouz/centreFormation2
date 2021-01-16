package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Inscription;
import model.Role;
import model.Stagiaire;
import model.Status;
import model.Utilisateur;
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
        String sql = " select utilisateur.IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,IdRole,DenomRole\n "
                + " ,IdStatus,DenomStatus,EstPaye,Signalisation \n "
                + " from utilisateur left join roles on utilisateur.Role = roles.IdRole left join Status on utilisateur.Status = Status.IdStatus left join inscrire on inscrire.IdUtilisateur = utilisateur.IdUtilisateur where inscrire.IdUtilisateur = ? ";
        
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUtilisateur());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscription = new Inscription(
                        new Stagiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)), new Status(rs.getInt(11), rs.getString(12))),
                        rs.getBoolean(12),
                        rs.getBoolean(13));
                listInscription.add(inscription);
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
}
