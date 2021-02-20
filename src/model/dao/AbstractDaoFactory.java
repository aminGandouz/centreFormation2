package model.dao;

public abstract class AbstractDaoFactory {

    private static AbstractDaoFactory factory;

    public static AbstractDaoFactory getFactory() {
        return factory;
    }

    public static void setFactory(AbstractDaoFactory factory) {
        AbstractDaoFactory.factory = factory;
    }

    public abstract CentreDao createCentreDao();

    public abstract UtilisateurDao createUtilisateurDao();

    public abstract StagiaireDao createStagiaireDao();

    public abstract FormationDao createFormationDao();

    public abstract AdminDao createAdminDao();

    public abstract SessionDao createSessionDao();

    public abstract InscriptionDao createInscriptionDao();

    public abstract UtilisateurDao createUtilisateurDaoDao();

}
