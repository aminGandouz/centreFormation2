package model;

import java.util.List;
import model.dao.AbstractDaoFactory;
import model.dao.StagiaireDao;
import model.dao.UtilisateurDao;

public class Stagiaire extends Utilisateur {

    private Status status;
    private Boolean estLoge;
    private Boolean estPaye;
    private List<Inscription> listInscription;

    public Stagiaire() {
    }

    public Stagiaire(Integer idUtilisateur, String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role, Status status) {
        super(idUtilisateur, nom, prenom, adresse, telephone, email, login, password, role);
        this.status = status;
    }

    public Stagiaire(String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role, Status status) {
        super(nom, prenom, adresse, telephone, email, login, password, role);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getEstLoge() {
        return estLoge;
    }

    public void setEstLoge(Boolean estLoge) {
        this.estLoge = estLoge;
    }

    public Boolean getEstPaye() {
        return estPaye;
    }

    public void setEstPaye(Boolean estPaye) {
        this.estPaye = estPaye;
    }

    public List<Inscription> getListInscription() {
        listInscription = getListDesInscriptions();
        return listInscription;
    }

    public void setListInscription(List<Inscription> listInscription) {
        this.listInscription = listInscription;
    }

    public void ajoutStagiaire() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        stagiaireDao.addStagiaire(this);
    }

    public void updateStagiaire() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        stagiaireDao.updateStagiaire(this);
    }

    public List<Inscription> getListDesInscriptions() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        return stagiaireDao.getListDesInscriptions(this);
    }

    public List<Inscription> getListFormationAvecNom(String nomFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UtilisateurDao utilisateurDao = factory.createUtilisateurDao();
        return utilisateurDao.getListFormationAvecNom(nomFormation);
    }

    public Inscription getInscritpionById(int choixInscription) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        return stagiaireDao.getInscritpionById(choixInscription);
    }

    public Boolean annulerInscription(Inscription inscription) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        return stagiaireDao.annulerInscription(inscription);
    }

    public List<Inscription> getListInscriptionNonPayer() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        return stagiaireDao.getListInscriptionNonPayer(this);
    }

    public Boolean signalerPaiement(Inscription inscription) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        return stagiaireDao.signalerPaiement(inscription);
    }

    public String toString() {
        return super.toString() + "Stagiaire{" + "status=" + status + ", estLoge=" + estLoge + ", estPaye=" + estPaye + ", listInscription=" + listInscription + '}';
    }

}
