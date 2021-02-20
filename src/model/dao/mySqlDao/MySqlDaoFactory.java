package model.dao.mySqlDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.dao.AbstractDaoFactory;
import model.dao.AdminDao;
import model.dao.CentreDao;
import model.dao.FormationDao;
import model.dao.InscriptionDao;
import model.dao.SessionDao;
import model.dao.StagiaireDao;
import model.dao.UtilisateurDao;

public class MySqlDaoFactory extends AbstractDaoFactory {

    private static MySqlDaoFactory instance;

    private MySqlDaoFactory() {
    }

    static {
        instance = new MySqlDaoFactory();
    }

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public CentreDao createCentreDao() {
        return MySqlCentreDao.getInstance();
    }

    public Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/centreformation", "carmelo", "carmelo");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UtilisateurDao createUtilisateurDao() {
        return MySqlUtilisateurDao.getInstance();
    }

    @Override
    public StagiaireDao createStagiaireDao() {
        return MySqlStagiaireDao.getInstance();
    }

    @Override
    public FormationDao createFormationDao() {
        return MySqlFormationDao.getInstance();
    }

    @Override
    public AdminDao createAdminDao() {
        return MySqlAdminDao.getInstance();
    }

    @Override
    public SessionDao createSessionDao() {
        return MySqlSessionDao.getInstance();
    }

    @Override
    public InscriptionDao createInscriptionDao() {
        return MySqlInscriptionDao.getInstance();
    }

    @Override
    public UtilisateurDao createUtilisateurDaoDao() {
        return MySqlUtilisateurDao.getInstance();
    }
}
