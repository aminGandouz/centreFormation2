package model;

import model.dao.AbstractDaoFactory;
import model.dao.AdminDao;

public class Formateur extends Utilisateur {

    public Formateur() {
    }

    public Formateur(Integer idUtilisateur, String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role) {
        super(idUtilisateur, nom, prenom, adresse, telephone, email, login, password, role);
    }

    public Formateur(String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role) {
        super(nom, prenom, adresse, telephone, email, login, password, role);
    }

    public void ajoutFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao adminDao = factory.createAdminDao();
        adminDao.ajoutFormateur(this);
    }

    public void effacerFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao adminDao = factory.createAdminDao();
        adminDao.effacerFormateur(this);
    }

    public void updateFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao adminDao = factory.createAdminDao();
        adminDao.updateFormateur(this);
    }

    public void deleteFormateurByName(String formateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        AdminDao adminDao = factory.createAdminDao();
        adminDao.deleteFormateurByName(formateur);
    }
    
}
