package model;

import java.util.List;
import model.dao.AbstractDaoFactory;
import model.dao.AdminDao;
import model.dao.CentreDao;
import model.dao.FormationDao;
import model.dao.SessionDao;
import model.dao.UtilisateurDao;

public class Centre {

    private Formation formation = new Formation();
    private List<Formation> listFormations;
    private List<Status> listStatus;
    private List<Formation> listFormationParNom;
    private List<Session> listSession;
    private List<Session> listSessionParFormation;// a voir
    private List<Stagiaire> listStagiaireParSessionPrixStatus;// a voir 
    private List<Local> listLocaux;
    private List<Formateur> listFormateur;

    public Centre() {
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<Status> getListStatus() {
        return getAllStatus();
    }

    public void setListStatus(List<Status> listStatus) {
        this.listStatus = listStatus;
    }

    public List<Formation> getListFormationParNom() {
        return listFormationParNom;
    }

    public void setListFormationParNom(List<Formation> listFormationParNom) {
        this.listFormationParNom = listFormationParNom;
    }

    public List<Session> getListSession() {
        return listSession;
    }

    public void setListSession(List<Session> listSession) {
        this.listSession = listSession;
    }

    public List<Session> getListSessionParFormation() {
        return listSessionParFormation;
    }

    public void setListSessionParFormation(List<Session> listSessionParFormation) {
        this.listSessionParFormation = listSessionParFormation;
    }

    public List<Stagiaire> getListStagiaireParSessionPrixStatus() {
        return listStagiaireParSessionPrixStatus;
    }

    public void setListStagiaireParSessionPrixStatus(List<Stagiaire> listStagiaireParSessionPrixStatus) {
        this.listStagiaireParSessionPrixStatus = listStagiaireParSessionPrixStatus;
    }

    public List<Formation> getListFormations() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listFormations = centreDao.getListFormation();
        return listFormations;
    }

    public List<Session> getListSessionAvecNom(Formateur formateur) {
        return formation.getListSessionAvecNom(formateur);
    }

    public List<Session> getListSessionByName(String nameSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionByNameFormation(nameSession);
    }

    public List<Session> getListSessionByNameFormateur(String nomFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionByNameFormateur(nomFormateur);
    }

    public List<Status> getAllStatus() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listStatus = centreDao.getAllStatus();
        return listStatus;
    }

    public Formation getFormationById(Integer idFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormationDao formationDao = factory.createFormationDao();
        return formationDao.getFormationById(idFormation);
    }

    public List<Formateur> getAllFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao adminDao = factory.createAdminDao();
        listFormateur = adminDao.getListFormateurs();
        return listFormateur;
    }

    public Utilisateur getUserById(Integer idUtilisateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UtilisateurDao utilisateurDao = factory.createUtilisateurDaoDao();
        return utilisateurDao.getUserById(idUtilisateur);
    }

    public List<Local> getAllLocaux() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listLocaux = centreDao.getAllLocaux();
        return listLocaux;
    }

    public Local getLocalById(int idLocal) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getLocalById(idLocal);
    }

    public List<Session> getListSessionByIdFormation(Integer idFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionByIdFormation(idFormation);
    }

    public List<Formateur> getListFormateurs() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao formateurDao = factory.createAdminDao();
        return formateurDao.getListFormateurs();
    }

    public Formateur getFormateurAvecNom(String nomFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao formateurDao = factory.createAdminDao();
        return formateurDao.getFormateurByName(nomFormateur);
    }

    public List<Formation> getListFormationDispoPOurFormateur(Integer idUtilisateur) {
        List<Formation> listFormationDispoPOurFormateur;
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listFormationDispoPOurFormateur = centreDao.getListFormationByIdFormateur(idUtilisateur);
        return listFormationDispoPOurFormateur;
    }

    public void ajoutFormationAuFormateur(Integer idUtilisateur, Integer idFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        centreDao.ajoutFormationAuFormateur(idUtilisateur, idFormation);
    }

    public List<Formation> getListDuFormateur(Integer idUtilisateur) {
        List<Formation> listFormationDuFormateur;
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listFormationDuFormateur = centreDao.getListDuFormateur(idUtilisateur);
        return listFormationDuFormateur;
    }

    public Boolean deleteFormationDuFormateur(int idFormateur, int idFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.deleteFormationDuFormateur(idFormateur, idFormation);
    }

    public Boolean isFormateurExist(String formateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.isFormateurExist(formateur);
    }

    public List<Formation> getListFormationsByNameFormation(String nomFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getListFormationsByNameFormation(nomFormation);
    }

    public List<Formateur> getFormateurByFormation(Formation form,Session session) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao formateurDao = factory.createAdminDao();
        return formateurDao.getFormateurByFormation(form,session);
    }

    public List<Local> getLocauxDispo(Session session) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getLocauxDispo(session);
    }

    public List<Session> getListSessionDispoByIdFormation(Integer choixDeLaFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionDispoByIdFormation(choixDeLaFormation);
    }

    public Session getListSessionByIdSession(Integer idSess) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionByIdSession(idSess);
    }

}

//    public List<Session> getListFormationByNameFormateur(String nameSession) {
//        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
//        SessionDao sessionDao = factory.createSessionDao();
//        return sessionDao.getListFormationAvecNom(nameSession);
//    }
