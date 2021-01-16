package model;

import model.dao.AbstractDaoFactory;
import model.dao.InscriptionDao;

public class Inscription {

    /* TODO ajouter le prix */
    private Stagiaire stagiaire;
    private Boolean estPaye;
    private Boolean signalisation;
    // private Float prix;

    public Inscription() {
    }

    public Inscription(Stagiaire stagiaire, Boolean estPaye, Boolean signalisation) {
        this.stagiaire = stagiaire;
        this.estPaye = estPaye;
        this.signalisation = signalisation;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
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

    public void ajoutStagiaire(Integer idUtilisateur, Integer session) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        InscriptionDao inscriptionDao = factory.createInscriptionDao();
        inscriptionDao.ajoutStagiaire(idUtilisateur, session);
    }
}
