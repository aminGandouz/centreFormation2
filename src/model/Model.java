
package model;

import java.util.List;


public class Model {
    private final Session sess;  
    private final Formation form; 
    private final Admin admin; 
    private final Stagiaire stagiaire; 
    private final Inscription inscription;
    private final Formateur formateur;
    private final Centre centre;

    public Model() {
        this.admin = new Admin();
        this.sess = new Session();
        this.form=new Formation();
        this.stagiaire = new Stagiaire();
        this.inscription = new Inscription();
        this.formateur = new Formateur();
        this.centre = new Centre();
    }

    public Formateur getFormateur() {
        return formateur;
    }     
    
    public Session getSess() {
        return sess;
    }

    public Formation getForm() {
        return form;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public Centre getCentre() {
        return centre;
    }
//    ////////// verifier les 2 méthodes ///////////
//    public List<Inscription> getListDesInscriptions(int idUtilisateur) {
//        return stagiaire.getListDesInscriptions(idUtilisateur);
//    }

    public List<Formation> getListFormations() {
        return centre.getListFormations();
    }
}
