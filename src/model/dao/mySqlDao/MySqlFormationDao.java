package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Formateur;
import model.Formation;
import model.Local;
import model.Role;
import model.Session;
import model.dao.FormationDao;

public class MySqlFormationDao implements FormationDao {

    private static MySqlFormationDao instance;

    static {
        instance = new MySqlFormationDao();
    }

    public static MySqlFormationDao getInstance() {
        return instance;
    }
    
    private MySqlFormationDao() {
    }

    @Override
    public List<Formation> getListFormationAvecNom(String nomFormation) {
        List<Formation> listFormation = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable` FROM `formation` WHERE Intitule LIKE ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nomFormation + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Formation formation = new Formation(rs.getInt(1),
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
                listFormation.add(formation);
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListFormationAvecNom(String nomFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listFormation;
    }

    @Override
    public void ajoutFormation(Formation f ) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `formation` (`Intitule`, `Prix`, `Duree`, `MaxParticipant`,`NbreParticipantMin`) values(?,?,?,?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, f.getIntitule());
            ps.setFloat(2, f.getPrix());
            ps.setInt(3, f.getDuree());
            ps.setInt(4, f.getMaxParticipant());
            ps.setInt(5, f.getNbreParticipantMin());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL ajoutFormation()");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public void effacerFormation(String formation) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " update `formation` set Enable = ? WHERE `intitule` = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setString(2, formation);
            ps.executeUpdate();
        } catch (SQLException e) {
            //System.err.println(" Probleme avec la requete SQL effacerFormation(String formation); \n" + e.getMessage());
            //System.out.println("Probleme avec la requete SQL effacerFormation(String formation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }
    /* TODO verifier dans le code car j'en recupère plusieurs */
    @Override
    public Formation getFormationAvecNom(String nomFormation) {
        Formation formation = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable` FROM `formation` WHERE Intitule LIKE ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nomFormation + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt(1),
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getFormationAvecNom(String nomFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return formation;
    }
    @Override
    public Formation getFormationById(Integer idFormation) {
        Formation formation = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable` FROM `formation` WHERE IdFormation = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormation);
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt(1),
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getFormationById(Integer idFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return formation;
    }


    @Override
    public void updateFormation(int idFormation, String nomFormation, float prix, int duree, int maxParticipant,int nbreParticipantMin) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update formation set Intitule = ?,Prix = ? ,Duree = ? ,MaxParticipant = ?, NbreParticipantMin = ?  where idFormation = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nomFormation);
            ps.setFloat(2, prix);
            ps.setInt(3, duree);
            ps.setInt(4, maxParticipant);
            ps.setInt(5, nbreParticipantMin);
            ps.setInt(6, idFormation);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL updateFormation(....) ");
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }
    /* TODO si le formateur prend un status le rajouter */
    @Override
    public List<Session> getListSessionAvecNom(Formateur formateur) {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select s.IdSession,s.IdFormation,f.Intitule,f.Prix,f.Duree,f.MaxParticipant,f.NbreParticipantMin,  s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n " +
" st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n " +
" from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st where s.IdFormation = f.IdFormation and s.IdFormateur = u.IdUtilisateur and s.Local = lo.IdLocal and u.Role = ro.IdRole and u.Status = st.IdStatus and s.IdFormateur =  ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, formateur.getIdUtilisateur());
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt(1),                   
                        new Formateur(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                                new Role(rs.getInt(16), rs.getString(17))),
                        rs.getDate(20), rs.getDate(21),
                        new Local(rs.getInt(22), rs.getString(23)));
                listSession.add(session);
            }//new Status(rs.getInt(18), rs.getString(19))
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListSessionAvecNom(Formateur formateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listSession;
    }
}
