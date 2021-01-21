package model;

import model.dao.AbstractDaoFactory;
import model.dao.InscriptionDao;

public class Inscription {

    private Integer idInscription;
    private Boolean estPaye;
    private Boolean signalisation;
    private Float prix;

    public Inscription() {
    }

    public Inscription(Integer idInscription, Boolean estPaye, Boolean signalisation, Float prix) {
        this.idInscription = idInscription;
        this.estPaye = estPaye;
        this.signalisation = signalisation;
        this.prix = prix;
    }

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer isInscription) {
        this.idInscription = isInscription;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Boolean getEstPaye() {
        return estPaye;
    }

    public void setEstPaye(Boolean estPaye) {
        this.estPaye = estPaye;
    }

    public Boolean getSignalisation() {
        return signalisation;
    }

    public void setSignalisation(Boolean signalisation) {
        this.signalisation = signalisation;
    }

    public Boolean ajoutStagiaire(Stagiaire stagiaire, Session session) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        InscriptionDao inscriptionDao = factory.createInscriptionDao();
        return inscriptionDao.ajoutStagiaire(stagiaire, session);
    }

    public void updateEstPaye() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        InscriptionDao inscriptionDao = factory.createInscriptionDao();
        inscriptionDao. updateEstPaye(this);
    }

}
