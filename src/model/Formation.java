package model;

import java.util.List;
import model.dao.AbstractDaoFactory;
import model.dao.FormationDao;
import model.dao.SessionDao;

public class Formation {

    private Integer idFormation;
    private String intitule;
    private Float prix;
    private Integer duree;
    private Integer maxParticipant;
    private Integer nbreParticipantMin;
    private List<Session> listSession;
    private List<Session> listSessionByFormation;

    public List<Session> getListSessionByFormation() {
        return getListSessionByNameFormation(intitule);
    }

    public void setListSessionByFormation(List<Session> listSessionByFormation) {
        this.listSessionByFormation = listSessionByFormation;
    }

    public List<Session> getListSession() {
        return getAllSession();
    }

    public void setListSession(List<Session> listSession) {
        this.listSession = listSession;
    }

    public Formation() {
    }

    public Formation(Integer idFormation, String intitule, Float prix, Integer duree, Integer maxParticipant, Integer nbreParticipantMin) {
        this.idFormation = idFormation;
        this.intitule = intitule;
        this.prix = prix;
        this.duree = duree;
        this.maxParticipant = maxParticipant;
        this.nbreParticipantMin = nbreParticipantMin;
    }

    public Formation(String intitule, Float prix, Integer duree, Integer maxParticipant, Integer nbreParticipantMin) {
        this.intitule = intitule;
        this.prix = prix;
        this.duree = duree;
        this.maxParticipant = maxParticipant;
        this.nbreParticipantMin = nbreParticipantMin;
    }

    public Integer getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(Integer maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public Integer getNbreParticipantMin() {
        return nbreParticipantMin;
    }

    public void setNbreParticipantMin(Integer nbreParticipantMin) {
        this.nbreParticipantMin = nbreParticipantMin;
    }

    // laisser ici 
    public void ajoutFormation() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormationDao formationDao = factory.createFormationDao();
        formationDao.ajoutFormation(this);
    }

    public static void effacerFormation(String formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormationDao formationDao = factory.createFormationDao();
        formationDao.effacerFormation(formation);
    }

    public static void updateFormation(int idFormation, String nomFormation, float prix, int duree, int maxParticipant, int nbreParticipantMin) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormationDao formationDao = factory.createFormationDao();
        formationDao.updateFormation(idFormation, nomFormation, prix, duree, maxParticipant, nbreParticipantMin);
    }

    //////// a mettre dans la classe centre //////// 
    public Formation getFormationAvecNom(String nomFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormationDao formationDao = factory.createFormationDao();
        return formationDao.getFormationAvecNom(nomFormation);
    }

    ////// changer car la methode se trouve dans formation et session //////
    ////// changer car la methode se trouve dans formation et session //////
    ////// changer car la methode se trouve dans formation et session //////
    public List<Session> getListSessionAvecNom(Formateur formateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionByNameFormateur(formateur.getNom());
//        FormationDao formationDao = factory.createFormationDao();
//        return formationDao.getListSessionAvecNom(formateur);
    }

    public List<Session> getAllSession() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getAllSession();
    }

    public Session getSessionByIdSession(Integer IdSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getSessionByIdSession(IdSession);
    }

    public List<Session> getListSessionByNameFormation(String nameFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getListSessionByNameFormation(nameFormation);
    }    
}
