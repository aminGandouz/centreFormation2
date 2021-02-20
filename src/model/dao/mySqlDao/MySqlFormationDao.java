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
        String sql = " SELECT `IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable` FROM `formation` WHERE Intitule LIKE ? and enable = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nomFormation + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Formation formation = new Formation(rs.getInt(1),
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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
    public void ajoutFormation(Formation f) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `formation` (`Intitule`, `Prix`, `Duree`, `MaxParticipant`,`NbreParticipantMin`,`enable`) values(?,?,?,?,?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, f.getIntitule());
            ps.setFloat(2, f.getPrix());
            ps.setInt(3, f.getDuree());
            ps.setInt(4, f.getMaxParticipant());
            ps.setInt(5, f.getNbreParticipantMin());
            ps.setInt(6, 1);
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
    public Boolean effacerFormation(Formation formation) {
        Boolean ok = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ifFormationAlone = " select IdFormation from formation where formation.IdFormation= ? and formation.IdFormation not in (select IdFormation from session) ";
        String ifSessionExist = " select IdSession from session where session.IdFormation = ? and DATEDIFF(session.DateDebut,now())>? and session.IdSession not in(select idSession from inscrire) ";
        String sql = " update `formation` set Enable = ? WHERE `IdFormation` = ? ";
        //String sql2 = " update `session` set Enable = ? WHERE session.IdSession = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(ifFormationAlone);
            ps.setInt(1, formation.getIdFormation());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = c.prepareStatement(sql);
                ps.setBoolean(1, false);
                ps.setInt(2, formation.getIdFormation());
                ps.executeUpdate();
                ok = true;
            } else {
                ps = c.prepareStatement(ifSessionExist);
                ps.setInt(1, formation.getIdFormation());
                ps.setInt(2, 0);
                rs = ps.executeQuery();

                while (rs.next()) {
//                    int idSess = rs.getInt(1);
//                    ps = c.prepareStatement(sql2);
//                    ps.setBoolean(1, false);
//                    ps.setInt(2, idSess);
//                    ps.executeUpdate();

                    ps = c.prepareStatement(sql);
                    ps.setBoolean(1, false);
                    ps.setInt(2, formation.getIdFormation());
                    ps.executeUpdate();
                    ok = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL effacerFormation(String formation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return ok;
    }

    @Override
    public Formation getFormationAvecNom(String nomFormation) {
        Formation formation = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable` FROM `formation` WHERE Intitule LIKE ? and enable = ?  ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nomFormation + "%");
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt(1),
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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
    public void updateFormation(Formation formation) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update formation set Intitule = ?,Prix = ? ,Duree = ? ,MaxParticipant = ?, NbreParticipantMin = ?  where idFormation = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, formation.getIntitule());
            ps.setFloat(2, formation.getPrix());
            ps.setInt(3, formation.getDuree());
            ps.setInt(4, formation.getMaxParticipant());
            ps.setInt(5, formation.getNbreParticipantMin());
            ps.setInt(6, formation.getIdFormation());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL updateFormation(Formation formation) ");
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public List<Session> getListSessionAvecNom(Formateur formateur) {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select s.IdSession,s.IdFormation,f.Intitule,f.Prix,f.Duree,f.MaxParticipant,f.NbreParticipantMin,  s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st where s.IdFormation = f.IdFormation and s.IdFormateur = u.IdUtilisateur and s.Local = lo.IdLocal and u.Role = ro.IdRole and u.Status = st.IdStatus and s.IdFormateur =  ? ";

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
            }
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

    @Override
    public Formation getFormationByName(String nom) {
        Formation formation = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdFormation`, `Intitule`, `Prix`, `Duree`, `MaxParticipant`, `NbreParticipantMin`, `enable` FROM `formation` WHERE Intitule = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt(1),
                        rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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
}
