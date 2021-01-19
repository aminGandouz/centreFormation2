package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Utilisateur;
import model.dao.SessionDao;

public class MySqlSessionDao implements SessionDao {

    private static MySqlSessionDao instance;

    static {
        instance = new MySqlSessionDao();
    }

    public static MySqlSessionDao getInstance() {
        return instance;
    }

    private MySqlSessionDao() {
    }

    @Override
    public List<Session> getListSessionByNameFormation(String formationName) {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Formation f = MySqlFormationDao.getInstance().getFormationAvecNom(formationName);
        if (f == null) {
            return null;
        }
        String sql3 = " select s.IdSession,s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st,donner as d\n "
                + " where s.IdFormation = f.IdFormation and s.IdFormateur = u.IdUtilisateur\n "
                + " and s.Local = lo.IdLocal and u.Role = ro.IdRole and u.Status = st.IdStatus and d.IdFormation = s.IdFormation  and ro.IdRole = ? and u.Nom = ? group by s.IdSession ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql3);
            ps.setInt(1, 3);
            ps.setString(2, formationName);
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt(1),
                        new Formateur(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11))),
                        rs.getDate(14), rs.getDate(15),
                        new Local(rs.getInt(16), rs.getString(17)));
                listSession.add(session);
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListSessionByName(String nameSession)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listSession;

    }

    @Override
    public List<Session> getListSessionByNameFormateur(String nomFormateur) {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Utilisateur u = MySqlUtilisateurDao.getInstance().getUserByName(nomFormateur);
        String sql = " select s.IdSession,\n "
                + " s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st \n "
                + " where s.IdFormation = f.IdFormation\n "
                + " and s.IdFormateur = u.IdUtilisateur\n "
                + " and s.Local = lo.IdLocal\n "
                + " and u.Role = ro.IdRole \n "
                + " and u.Status = st.IdStatus \n "
                + " and u.Nom = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, nomFormateur);
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt(1),
                        new Formateur(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11))),
                        rs.getDate(14), rs.getDate(15),
                        new Local(rs.getInt(16), rs.getString(17)));
                listSession.add(session);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListSessionByNameFormateur(String nomFormateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listSession;
    }

    @Override
    public Session getSessionByIdSession(Integer IdSession) {
        Session session = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql3 = " select s.IdSession,\n "
                + " s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st \n "
                + " where s.IdFormation = f.IdFormation\n "
                + " and s.IdFormateur = u.IdUtilisateur\n "
                + " and s.Local = lo.IdLocal\n "
                + " and u.Role = ro.IdRole \n "
                + " and u.Status = st.IdStatus \n "
                + " and s.IdSession = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql3);
            ps.setInt(1, IdSession);
            rs = ps.executeQuery();

            if (rs.next()) {
                session = new Session(rs.getInt(1),
                        new Formateur(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11))),
                        rs.getDate(14), rs.getDate(15),
                        new Local(rs.getInt(16), rs.getString(17)));
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getSessionByIdSession(Integer IdSession)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return session;

    }

    @Override
    public List<Session> getAllSession() {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql3 = " select s.IdSession,\n "
                + " s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st \n "
                + " where s.IdFormation = f.IdFormation\n "
                + " and s.IdFormateur = u.IdUtilisateur\n "
                + " and s.Local = lo.IdLocal\n "
                + " and u.Role = ro.IdRole \n "
                + " and u.Status = st.IdStatus ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql3);
            rs = ps.executeQuery();

            while (rs.next()) {
                Session session = new Session(rs.getInt(1),
                        new Formateur(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11))),
                        rs.getDate(14), rs.getDate(15),
                        new Local(rs.getInt(16), rs.getString(17)));
                listSession.add(session);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getAllSession()");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listSession;

    }

    @Override
    public void addSession(Session session, int idFormation) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `session` (`IdFormation`, `IdFormateur`, `DateDebut`, `DateFin`, `Local`) VALUES (?,?,?,?,?);";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormation);
            ps.setInt(2, session.getFormateur().getIdUtilisateur());
            ps.setDate(3, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(session.getDateFin().getTime()));
            ps.setInt(5, session.getLocal().getIdLocal());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL addSession(Session session,int idFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    /* TODO faire un update au lieu de delete */
    @Override
    public void deleteSessionById(Integer idSession) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "DELETE FROM `session` WHERE `IdSession` = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idSession);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL deleteSessionById(Integer idSession)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public List<Session> getListSessionByIdFormation(Integer idFormation) {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select s.IdSession,\n "
                + " s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,ro.IdRole,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st \n "
                + " where s.IdFormation = f.IdFormation\n "
                + " and s.IdFormateur = u.IdUtilisateur\n "
                + " and s.Local = lo.IdLocal\n "
                + " and u.Role = ro.IdRole \n "
                + " and u.Status = st.IdStatus\n "
                + " and s.IdFormation = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormation);
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt(1),
                        new Formateur(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11))),
                        rs.getDate(14), rs.getDate(15),
                        new Local(rs.getInt(16), rs.getString(17)));
                listSession.add(session);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListSessionByIdFormation(Integer idFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listSession;
    }

    /* TODO vérifier la requete */
    @Override
    public List<Session> getListSessionDispoByIdFormation(Integer choixDeLaFormation) {
        List<Session> listSession = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select s.IdSession,\n "
                + " s.IdFormateur,u.Nom,u.Prenom,u.Adresse,u.Telephone,u.Email,u.Login,u.Password,\n "
                + " (select count(inscrire.IdSession) from inscrire \n "
                + " join session on session.IdFormation = inscrire.IdSession\n "
                + " where session.IdSession = ? and DATEDIFF(session.DateFin,now())< ?) as IdRole\n "
                + " ,ro.DenomRole,\n "
                + " st.IdStatus,st.DenomStatus,s.DateDebut,s.DateFin,lo.IdLocal,lo.DenomLocal \n "
                + " from session as s,formation as f,locaux as lo,utilisateur as u,roles as ro,status as st \n "
                + " where s.IdFormation = f.IdFormation\n "
                + " and s.IdFormateur = u.IdUtilisateur\n "
                + " and s.Local = lo.IdLocal\n "
                + " and u.Role = ro.IdRole \n "
                + " and u.Status = st.IdStatus\n "
                + " and s.IdFormation = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, choixDeLaFormation);
            ps.setInt(2, 0);
            ps.setInt(3, choixDeLaFormation);
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt(1),
                        new Formateur(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11))),
                        rs.getDate(14), rs.getDate(15),
                        new Local(rs.getInt(16), rs.getString(17)));
                listSession.add(session);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListSessionDispoByIdFormation(Integer choixDeLaFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listSession;
    }

    @Override
    public Session getListSessionByIdSession(Integer idSess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inscription> getListInscription(Session sess) {
        List<Inscription> listInscription = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdInscription`, `EstPaye`, `Signalisation`, `prix` FROM `inscrire` \n " +
" WHERE inscrire.IdSession = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, sess.getIdSession());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscritpion = new Inscription(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getFloat(4));
                listInscription.add(inscritpion);
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL getListInscription(Session sess)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listInscription;
    }
}

