package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Formateur;
import model.Formation;
import model.Role;
import model.Session;
import model.dao.AdminDao;

public class MySqlAdminDao implements AdminDao {

    private static MySqlAdminDao instance;

    static {
        instance = new MySqlAdminDao();
    }

    public static MySqlAdminDao getInstance() {
        return instance;
    }

    private MySqlAdminDao() {
    }

    @Override
    public void ajoutFormateur(Formateur formateur) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer idRoleFormateur = 3;
        Integer idStatus = 3;
        String sql = " insert into utilisateur(Nom,Prenom,Adresse,Telephone,Email,Login,Password,Role,Status,Enable) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, formateur.getNom());
            ps.setString(2, formateur.getPrenom());
            ps.setString(3, formateur.getAdresse());
            ps.setString(4, formateur.getTelephone());
            ps.setString(5, formateur.getEmail());
            ps.setString(6, formateur.getLogin());
            ps.setString(7, formateur.getPassword());
            ps.setInt(8, idRoleFormateur);
            ps.setInt(9, idStatus);
            ps.setInt(10, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  ajoutFormateur(Formateur formateur) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }

    }

    @Override
    public void effacerFormateur(Formateur formateur) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String formateurExist = " SELECT DISTINCT idFormateur FROM session WHERE (session.dateDebut>CURDATE() OR session.dateFin>CURDATE()) AND idFormateur = ? ";
        String sql = "update `Utilisateur` set Enable = ? WHERE `nom` = ? and role = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(formateurExist);
            ps.setInt(1, formateur.getIdUtilisateur());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement(sql);
                ps.setBoolean(1, false);
                ps.setString(2, formateur.getNom());
                ps.setInt(3, formateur.getRole().getIdRole());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL effacerFormateur(Formateur formateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public List<Formateur> getListFormateurs() {
        List<Formateur> listFormateurs = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int IdRole = 3;
        String sql = " select IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,IdRole,DenomRole,IdStatus,DenomStatus from utilisateur join roles on utilisateur.Role = roles.IdRole \n"
                + " join Status on utilisateur.Status = Status.IdStatus where utilisateur.Role = ? and Enable = true ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, IdRole);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formateur formateur = new Formateur(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        new Role(rs.getInt(9), rs.getString(IdRole)));
                listFormateurs.add(formateur);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListFormateurs() ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listFormateurs;
    }

    @Override
    public Formateur getFormateurByName(String nomFormateur) {
        Formateur formateur = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer IdRole = 3;
        String sql = "select IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,\n"
                + "IdRole,DenomRole,\n"
                + "IdStatus,DenomStatus \n"
                + "from utilisateur,roles,status\n"
                + "where utilisateur.Role = roles.IdRole \n"
                + "and utilisateur.Status = status.IdStatus\n"
                + "and utilisateur.Role = ? \n"
                + "and utilisateur.Nom = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, IdRole);
            ps.setString(2, nomFormateur);
            rs = ps.executeQuery();

            if (rs.next()) {
                formateur = new Formateur(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        new Role(rs.getInt(9), rs.getString(IdRole)));
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getFormateurByName(String nomFormateur) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return formateur;
    }

    @Override
    public void updateFormateur(Formateur formateur) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update utilisateur set nom = ? ,prenom = ?,adresse = ?,telephone = ?,email = ?,login = ?,password = ?,role = ?  where idUtilisateur= ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, formateur.getNom());
            ps.setString(2, formateur.getPrenom());
            ps.setString(3, formateur.getAdresse());
            ps.setString(4, formateur.getTelephone());
            ps.setString(5, formateur.getEmail());
            ps.setString(6, formateur.getLogin());
            ps.setString(7, formateur.getPassword());
            ps.setInt(8, formateur.getRole().getIdRole());
            ps.setInt(9, formateur.getIdUtilisateur());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL updateFormateur(Formateur formateur) ");
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public void deleteFormateurByName(String formateur) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update `Utilisateur` set Enable = ? WHERE `nom` = ? and role = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setString(2, formateur);
            ps.setInt(3, 3);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL deleteFormateurByName(String formateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public List<Formateur> getFormateurByFormation(Formation form, Session sess) {
        List<Formateur> listFormateurs = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select distinct utilisateur.IdUtilisateur,Nom,Prenom,Adresse,Telephone,Email,Login,Password,IdRole,DenomRole,IdStatus,DenomStatus \n "
                + " from utilisateur \n "
                + " join roles on utilisateur.Role = roles.IdRole \n "
                + " join Status on utilisateur.Status = Status.IdStatus \n "
                + " join donner on utilisateur.IdUtilisateur = donner.IdUtilisateur\n "
                + " where donner.IdFormation = ? and utilisateur.IdUtilisateur not in (select session.IdFormateur from session where session.dateDebut < ? AND session.dateFin > ? OR session.dateDebut > ? AND session.dateFin < ? \n "
                + " group by utilisateur.IdUtilisateur) ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, form.getIdFormation());
            ps.setDate(2, new java.sql.Date(sess.getDateFin().getTime()));
            ps.setDate(3, new java.sql.Date(sess.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(sess.getDateFin().getTime()));
            ps.setDate(5, new java.sql.Date(sess.getDateDebut().getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                Formateur formateur = new Formateur(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        new Role(rs.getInt(9), rs.getString(10)));
                listFormateurs.add(formateur);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getFormateurByFormation(Formation form) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listFormateurs;
    }
}
