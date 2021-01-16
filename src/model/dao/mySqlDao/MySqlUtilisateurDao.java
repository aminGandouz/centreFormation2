package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Admin;
import model.Formateur;
import model.Inscription;
import model.Role;
import model.Stagiaire;
import model.Status;
import model.Utilisateur;
import model.dao.UtilisateurDao;

public class MySqlUtilisateurDao implements UtilisateurDao {

    private static MySqlUtilisateurDao instance;

    public static MySqlUtilisateurDao getInstance() {
        if (instance == null) {
            instance = new MySqlUtilisateurDao();
        }
        return instance;
    }

    private MySqlUtilisateurDao() {
    }
    
    @Override
    public Utilisateur getAuthentification(String login, String password) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Utilisateur user = null;
        String sql = " select IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,IdRole,DenomRole,IdStatus,DenomStatus from utilisateur left join roles on utilisateur.Role = roles.IdRole \n "
                + " left join Status on utilisateur.Status = Status.IdStatus WHERE utilisateur.Login = ? and utilisateur.Password = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(9) == 1) {
                    user = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            new Role(rs.getInt(9), rs.getString(10)));
                } else if (rs.getInt(9) == 2) {
                    user = new Stagiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            new Role(rs.getInt(9), rs.getString(10)),
                            new Status(rs.getInt(11), rs.getString(12)));
                } else if (rs.getInt(9) == 3) {
                    user = new Formateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            new Role(rs.getInt(9), rs.getString(10)));
                }
            }
        } catch (SQLException e) {
            System.out.println("Problème avec la requête SQL getAuthentification(String login, String password)");
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return user;
    }

    public Utilisateur getUserById(int idUser) {
        Utilisateur u = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,IdRole,DenomRole,IdStatus,DenomStatus from utilisateur left join roles on utilisateur.Role = roles.IdRole \n"
                + " left join Status on utilisateur.Status = Status.IdStatus WHERE utilisateur.IdUtilisateur = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();

            if (rs.next()) {
                switch (rs.getInt(9)) {
                    case 1://admin
                        u = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6),
                                rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)));
                        break;
                    case 2://stagiaire
                        u = new Stagiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6),
                                rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)),
                                new Status(rs.getInt(11), rs.getString(12)));
                        break;
                    case 3://formateur
                        u = new Formateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6),
                                rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)));
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getUserById(int idUser)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return u;
    }

    public Utilisateur getUserByName(String userName) {
        Utilisateur u = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,IdRole,DenomRole,IdStatus,DenomStatus from utilisateur left join roles on utilisateur.Role = roles.IdRole \n"
                + " left join Status on utilisateur.Status = Status.IdStatus  where utilisateur.Nom = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (rs.next()) {
                switch (rs.getInt(9)) {
                    case 1://admin
                        u = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6),
                                rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)));
                        break;
                    case 2://stagiaire
                        u = new Stagiaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6),
                                rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)),
                                new Status(rs.getInt(11), rs.getString(12)));
                        break;
                    case 3://formateur
                        u = new Formateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6),
                                rs.getString(7), rs.getString(8),
                                new Role(rs.getInt(9), rs.getString(10)));
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getUserByName(String userName)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return u;
    }

    @Override
    public List<Inscription> getListFormationAvecNom(String nomFormation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

//    static {
//        instance = new MySqlUtilisateurDao();
//    }
//        String sql2 = "select formation.intitule as intit ,session.dateDebut as dt , inscrire.estPaye as estPaye from utilisateur \n"
//                + "join inscrire on utilisateur.idUtilisateur = inscrire.idUtilisateur \n"
//                + "join session on session.idSession = inscrire.idSession \n"
//                + "join formation on formation.idFormation = session.idFormation\n"
//                + "where utilisateur.idUtilisateur = ?";


//         (Session) MySqlSessionDao.getInstance().getSessionByIdSession(rs.getInt(2))
