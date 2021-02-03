package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Status;
import model.dao.CentreDao;

public class MySqlCentreDao implements CentreDao {

    private static MySqlCentreDao instance;

    static {
        instance = new MySqlCentreDao();
    }

    public static MySqlCentreDao getInstance() {
        return instance;
    }

    private MySqlCentreDao() {
    }

    /**
     * ****** FORMATION ************
     */
    @Override
    public List<Formation> getListFormation() {
        List<Formation> listFormation = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdFormation,Intitule,Prix,Duree,MaxParticipant,NbreParticipantMin from formation where enable = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setBoolean(1,true);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                listFormation.add(formation);
            }
        } catch (SQLException e) {
            System.out.println("Problème avec la requete SQL getListFormation()");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listFormation;
    }

    @Override
    public List<Formation> getListFormationsByNameFormation(String nomFormation) {
        List<Formation> listFormation = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdFormation,Intitule,Prix,Duree,MaxParticipant,NbreParticipantMin from formation where intitule like ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nomFormation + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                listFormation.add(formation);
            }
        } catch (SQLException e) {
            System.out.println("Problème avec la requete SQL getListFormationsByNameFormation(String nomFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listFormation;
    }

    /**
     * ****** STATUS ************
     */
    public Status getStatusById(int idStatus) {
        Status status = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdStatus,DenomStatus from status where IdStatus = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idStatus);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = new Status(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(" Problème avec la requete SQL getStatusById(int idStatus) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return status;
    }

    @Override
    public Boolean ajoutStatus(Status newStatus) {
        Boolean ok = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statusExist = " select idStatus from status where DenomStatus = ?  ";
        String sql = "INSERT INTO `status` (`DenomStatus`, `reduction`) values(?,?)";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(statusExist);
            ps.setString(1, newStatus.getNomStatus());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement(sql);
                ps.setString(1, newStatus.getNomStatus());
                ps.setDouble(2, newStatus.getReduction());
                ps.executeUpdate();
                ok = true;
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL ajoutStatus(Status newStatus)");
            e.printStackTrace();
            ok = false;
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return ok;
    }

    @Override
    public List<Status> getAllStatus() {
        List<Status> listStatus = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdStatus,DenomStatus from Status ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Status status = new Status(rs.getInt(1), rs.getString(2));
                listStatus.add(status);
            }
        } catch (SQLException e) {
            System.out.println(" Problème avec la requete SQL getAllStatus() ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listStatus;
    }

    /**
     * ****** LOCAL ************
     */
    public Local getLocalById(int idLocal) {
        Local local = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdLocal,DenomLocal from locaux where IdLocal = ?  ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idLocal);
            rs = ps.executeQuery();
            if (rs.next()) {
                local = new Local(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(" Problème avec la requete SQL  getLocalById(int idLocal) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return local;
    }

    @Override
    public List<Local> getAllLocaux() {
        List<Local> listLocaux = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdLocal,DenomLocal from locaux ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Local local = new Local(rs.getInt(1), rs.getString(2));
                listLocaux.add(local);
            }
        } catch (SQLException e) {
            System.out.println(" Problème avec la requete SQL getAllLocaux() ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listLocaux;
    }

    @Override
    public List<Local> getLocauxDispo(Session session) {
        List<Local> listLocaux = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdLocal,DenomLocal from locaux\n "
                + " where IdLocal not in(select session.Local from session where locaux.idLocal = session.Local AND session.dateDebut < ? AND session.dateFin > ? OR session.dateDebut > ? AND session.dateFin < ? ) ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(session.getDateFin().getTime()));
            ps.setDate(2, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(3, new java.sql.Date(session.getDateFin().getTime()));
            ps.setDate(4, new java.sql.Date(session.getDateDebut().getTime()));

            rs = ps.executeQuery();
            while (rs.next()) {
                Local local = new Local(rs.getInt(1), rs.getString(2));
                listLocaux.add(local);
            }
        } catch (SQLException e) {
            System.out.println(" Problème avec la requete SQL getLocauxDispo(Session session) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listLocaux;
    }

    @Override
    public Boolean ajoutLocal(Local local) {
        Boolean ok = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String localExist = " Select IdLocal from locaux where DenomLocal = ?  ";
        String sql = " INSERT INTO `locaux` (`DenomLocal`) values(?)";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(localExist);
            ps.setString(1, local.getNomLocal());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = c.prepareStatement(sql);
                ps.setString(1, local.getNomLocal());
                ps.executeUpdate();
                ok = true;
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL ajoutStatus(Status newStatus)");
            e.printStackTrace();
            ok = false;
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return ok;
    }

    /**
     * ****** ROLE ************
     */
    public Role getRoleById(int idRole) {
        Role role = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " select IdRole,DenomRole from roles where IdRole = ?  ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idRole);
            rs = ps.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(" Problème avec la requete SQL getRoleById(int idRole) ");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return role;
    }

    /**
     * ****** FORMATEUR ************
     */
    @Override
    public List<Formation> getListFormationByIdFormateur(Integer idUtilisateur) {
        List<Formation> listFormationDuFormateur = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select formation.IdFormation,Intitule,Prix,Duree,MaxParticipant,NbreParticipantMin \n"
                + "from formation where IdFormation not in(select IdFormation from donner where donner.IdUtilisateur = ? ) ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idUtilisateur);
            rs = ps.executeQuery();

            while (rs.next()) {
                Formation formation = new Formation(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                listFormationDuFormateur.add(formation);
            }

        } catch (SQLException e) {
            System.out.println("Problème avec la requete SQL getListFormationByIdFormateur(Integer idUtilisateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listFormationDuFormateur;
    }

    @Override
    public void ajoutFormationAuFormateur(Integer idUtilisateur, Integer idFormation) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `donner` (`IdUtilisateur`, `IdFormation`) values(?,?)";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idUtilisateur);
            ps.setInt(2, idFormation);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL ajoutFormationAuFormateur(Integer idUtilisateur, Integer idFormation)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }

    @Override
    public List<Formation> getListDuFormateur(Integer idUtilisateur) {
        List<Formation> listDuFormateur = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select formation.IdFormation,Intitule,Prix,Duree,MaxParticipant,NbreParticipantMin \n"
                + "from formation join donner on formation.IdFormation = donner.IdFormation where donner.IdUtilisateur = ?";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idUtilisateur);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                listDuFormateur.add(formation);
            }
        } catch (SQLException e) {
            System.out.println("Problème avec la requete SQL getListDuFormateur(Integer idUtilisateur)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listDuFormateur;
    }

    @Override
    public Boolean deleteFormationDuFormateur(int idFormateur, int idFormation) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlSessionExist = " select idSession from session where session.IdFormation = ? and session.IdFormateur = ? and DATEDIFF(now(),DateFin) <= ? ";
        String sql = " DELETE FROM `donner` WHERE IdUtilisateur = ? and IdFormation = ?  ";
        Boolean erase = false;
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sqlSessionExist);
            ps.setInt(1, idFormation);
            ps.setInt(2, idFormateur);
            ps.setInt(3, 0);
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, idFormateur);
                ps.setInt(2, idFormation);
                ps.executeUpdate();
                erase = true;
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL deleteFormationDuFormateur(int idFormateur, int idFormation))");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return erase;
    }

    @Override
    public Boolean isFormateurExist(String formateur) {
        Boolean isFormateurExist = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idRole = 3;
        String sql = " select Nom from utilisateur where Nom = ? and Role = ? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, formateur);
            ps.setInt(2, idRole);
            rs = ps.executeQuery();
            if (rs.next()) {
                isFormateurExist = true;
            }
        } catch (SQLException e) {
            System.out.println("Problème avec la requete SQL isFormateurExist(Formateur f)");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return isFormateurExist;
    }

    /**
     * ****** INSCRIPTION ************
     */
    @Override
    public List<Inscription> getListDesInscriptions() {
        List<Inscription> listInscription = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdInscription`, `IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`, `prix` FROM `inscrire` where signalisation = ? and EstPaye = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setBoolean(2, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscritpion = new Inscription(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getFloat(4));
                listInscription.add(inscritpion);
            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getListDesInscriptions()");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return listInscription;
    }

    @Override
    public Inscription getInscritpionById(int choix) {
        Inscription inscription = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT `IdInscription`, `IdUtilisateur`, `IdSession`, `EstPaye`, `Signalisation`, `prix` FROM `inscrire` where IdInscription = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, choix);
            rs = ps.executeQuery();
            while (rs.next()) {
                inscription = new Inscription(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getFloat(4));

            }
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL  getListDesInscriptions()");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
        return inscription;
    }

    /**
     * ****** CLEAN ************
     */
    @Override
    public void cleanDB() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " call proc_cleanDB() ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
//            ps.setBoolean(1, false);
//            ps.setInt(2, 365);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete SQL cleanDB()");
            e.printStackTrace();
        } finally {
            MySqlDaoFactory.closeResultSet(rs);
            MySqlDaoFactory.closeStatement(ps);
            MySqlDaoFactory.closeConnection(c);
        }
    }
}
