package model;

import model.dao.AbstractDaoFactory;
import model.dao.UtilisateurDao;

public abstract class Utilisateur {

    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String login;
    private String password;
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur, String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static Utilisateur authentification(String login, String password) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UtilisateurDao utilisateurDao = factory.createUtilisateurDao();
        return utilisateurDao.getAuthentification(login, password);
    }

    public static Utilisateur authentificationLogin(String loginString) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UtilisateurDao utilisateurDao = factory.createUtilisateurDao();
        return utilisateurDao.authentificationLogin(loginString);
    }
}
