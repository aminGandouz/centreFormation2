package controler;

import static controler.ControlerUtils.controler;
import static controler.ControlerUtils.ctrlFormation;
import static controler.ControlerUtils.model;
import static controler.ControlerUtils.s;
import java.util.List;
import model.Admin;
import model.Formateur;
import model.Formation;
import model.Local;
import model.Session;
import vue.Accueil;
import vue.VueAdmin;
import vue.VueFormateur;

public class ControlerFormation implements ControlerUtils {

    private final Accueil vAccueil = new Accueil();
    private final VueAdmin vueAdmin = new VueAdmin();
    private final VueFormateur vueFormateur = new VueFormateur();
    private List<Formation> listFormations;
    private List<Session> listSessionFormation;
    private String nom = null;
    private Float prix = null;
    private Integer duree = null;
    private Integer maxParticipant = null;
    private Integer nbreParticipantMin = null;
    private String prixString = null;
    private String dureeString = null;
    private String maxParticipantString = null;
    private String nbreParticipantMinString = null;

    public void gererFormation() {
        vueAdmin.menuGererFormation();
        int choixDuMenus = choixMenus(1, 5);

        switch (choixDuMenus) {
            case 1:
                vueAdmin.ajouterUneFormation();
                ajoutFormation();
                vueAdmin.menuGererFormation();
                gererFormation();
            case 2:
                vueAdmin.effaceFormation();
                effacerFormation();
                gererFormation();
                break;
            case 3:
                vueAdmin.updateFormation();
                listFormations = model.getListFormations();
                vueAdmin.afficherFormations(listFormations);
                updateFormaton();
                gererFormation();
                break;
            case 4:
                vueAdmin.afficherLesFormations();
                listFormations = model.getListFormations();
                vueAdmin.afficherFormations(listFormations);
                gererFormation();
                break;
            case 5:
                ctrlAdmin.menuAdmin((Admin) controler.getUserConnecte());
                break;

        }
    }

    private void ajoutFormation() {
        s.nextLine();
        Formation f = new Formation();

        do {
            vueAdmin.entrerNomFormation();
            nom = s.nextLine();
        } while (nom == null || nom.trim().isEmpty());// trim retire les espaces avt et apres et Si ce qui reste est vide => le string de base n'avait que des espaces 
        f.setIntitule(nom);

        do {
            vueAdmin.entrerLePrixDeLAFormation();
            prixString = s.nextLine();

        } while (!prixString.matches("\\d+") || Float.parseFloat(prixString) <= 0);
        prix = Float.parseFloat(prixString);
        f.setPrix(prix);

        do {
            vueAdmin.enterLaDuréeDeLaFormation();
            dureeString = s.nextLine();
        } while (!dureeString.matches("\\d+") || Integer.parseInt(dureeString) <= 0);
        duree = Integer.parseInt(dureeString);
        f.setDuree(duree);

        do {
            vueAdmin.enterMaxParticipant();
            maxParticipantString = s.nextLine();
        } while (!maxParticipantString.matches("\\d+") || Integer.parseInt(maxParticipantString) <= 0);
        maxParticipant = Integer.parseInt(maxParticipantString);
        f.setMaxParticipant(maxParticipant);

        do {
            vueAdmin.enterMinPartricipant();
            nbreParticipantMinString = s.nextLine();
        } while (!nbreParticipantMinString.matches("\\d+") || Integer.parseInt(nbreParticipantMinString) <= 0);
        nbreParticipantMin = Integer.parseInt(nbreParticipantMinString);
        f.setNbreParticipantMin(nbreParticipantMin);
        f.ajoutFormation();
    }

    private void effacerFormation() {
        String formation = null;
        s.nextLine();
        do {
            formation = s.nextLine();
        } while (formation == null || formation.trim().isEmpty());
        Formation form = null;
        form = model.getForm().getFormationAvecNom(formation);
        if (form == null) {
            vueAdmin.erreurFormation();
        } else {
            Formation.effacerFormation(formation);
            vueAdmin.deleteDone(formation);
        }
    }

    private void updateFormaton() {
        s.nextLine();
        String intitule = null;
        do {
            vueAdmin.entrerCoordonnee();
            intitule = s.nextLine();
        } while (intitule == null || intitule.trim().isEmpty());
        Formation form = model.getForm().getFormationAvecNom(intitule);
        if (form == null) {
            /* TODO mettre un message d'erreur */
            ctrlFormation.gererFormation();
        } else {
            vueAdmin.afficherLaFormation(form);
            do {
                vueAdmin.entrerNomFormation();
                nom = s.nextLine();
            } while (nom == null || nom.trim().isEmpty());// trim retire les espaces avt et apres et Si ce qui reste est vide => le string de base n'avait que des espaces 
            form.setIntitule(intitule);
            do {
                vueAdmin.entrerLePrixDeLAFormation();
                prixString = s.nextLine();

            } while (!prixString.matches("\\d+") || Float.parseFloat(prixString) <= 0);
            prix = Float.parseFloat(prixString);
            form.setPrix(prix);

            do {
                vueAdmin.enterLaDuréeDeLaFormation();
                dureeString = s.nextLine();
            } while (!dureeString.matches("\\d+") || Integer.parseInt(dureeString) <= 0);
            duree = Integer.parseInt(dureeString);
            form.setDuree(duree);
            do {
                vueAdmin.enterMaxParticipant();
                maxParticipantString = s.nextLine();
            } while (!maxParticipantString.matches("\\d+") || Integer.parseInt(maxParticipantString) <= 0);
            maxParticipant = Integer.parseInt(maxParticipantString);
            form.setMaxParticipant(maxParticipant);
            do {
                vueAdmin.enterMinPartricipant();
                nbreParticipantMinString = s.nextLine();
            } while (!nbreParticipantMinString.matches("\\d+") || Integer.parseInt(nbreParticipantMinString) <= 0);
            nbreParticipantMin = Integer.parseInt(nbreParticipantMinString);
            form.setMaxParticipant(nbreParticipantMin);
        }
        form.updateFormation();
    }

    @Override
    public void erreur() {
        vAccueil.erreur();

    }

    public void rechercherSessionFormateur(Admin admin) {
        Formation f = new Formation();
        vueAdmin.faireUnChoixValide();
        List<Formation> listFormation = model.getCentre().getListFormations();
        vueAdmin.afficherFormations(listFormation);
        vueAdmin.entrerFormationId();
        if (s.hasNextInt()) {
            int idFormation = s.nextInt();
            f = model.getCentre().getFormationById(idFormation);
            if (f != null) {
                vueAdmin.afficherLaFormation(f);
                vueAdmin.rechercherSessionFormateur();
                List<Session> listSession = model.getForm().getListSession();
                vueAdmin.afficherListSession(listSession);
                vueAdmin.entrerIdSession();
                if (s.hasNextInt()) {
                    int idSession = s.nextInt();
                    Session sess = new Session();
                    sess = model.getForm().getSessionByIdSession(idSession);
                    if (sess == null) {
                        vueAdmin.erreur();
                    } else {
                        vueAdmin.afficherSession(sess);
                    }
                } else {
                    vueAdmin.erreurFormation();
                    rechercherSessionFormateur(admin);
                }
            } else {
                vueAdmin.erreurFormation();
                rechercherSessionFormateur(admin);
            }
        }

    }

    public void gererSession() {
        vueAdmin.entrerIdFormation();
        int choix = choixMenus2(1, 2);

        switch (choix) {
            case 1:

                listFormations = model.getListFormations();
                vueAdmin.afficherFormations(listFormations);
//                vueAdmin.faireUnChoixValide();
//                int idFormation = s.nextInt();
                gererLaSession(listFormations);
//                Formation f = new Formation();
//                f = model.getCentre().getFormationById(idFormation);
//                if (f == null) {
//                    vueAdmin.erreur();
//                } else {
//                    gererSessionFormation(f.getIdFormation());
//                }

                break;
            case 2:
                ctrlAdmin.menuAdmin((Admin) controler.getUserConnecte());
                break;
        }
    }

    public void gererSessionFormation(Formation form) {
        vueAdmin.gererSession();
        int choixSess = choixMenus(1, 6);

        switch (choixSess) {
            case 1://Créer
                vueAdmin.creerSession();
                ctrlSession.addSession(form);
                gererSessionFormation(form);
                break;
            case 2://Effacer
                vueAdmin.deleteSession();
                ctrlSession.deleteSession(form);
                gererSessionFormation(form);
                break;
            case 3://Editer
                vueAdmin.editerSession();
                editerSessionDeFormation(form);
                gererSessionFormation(form);
                break;
            case 4://Afficher les sessions
                System.out.println(form);
                listSessionFormation = model.getCentre().getListSessionByIdFormation(form.getIdFormation());
                vueAdmin.afficherListSessionAvecNbParticipant(listSessionFormation, form);
                gererSessionFormation(form);
                break;
            case 5://Afficher une session
                listInscriptionSession(form);
                gererSessionFormation(form);
                break;

            case 6:
                vueAdmin.retour();
                gererSession();
                break;
        }
    }

    private void gererLaSession(List<Formation> listForma) {
        int idFormation = 0;
        vueAdmin.faireUnChoixValide();
        Formation f = new Formation();
        // gérer erreur 
        if (s.hasNextInt()) {
            idFormation = s.nextInt();
        } else {
            vueAdmin.faireUnChoixValide();
            gererLaSession(listForma);
        }
        f = model.getCentre().getFormationById(idFormation);
        if (f != null) {
            vueAdmin.afficherLaFormation(f);
            gererSessionFormation(f);
        } else {
            vueAdmin.erreurFormation();
            gererSession();
        }

        int choixSession = choixMenus2(1, 2);
        switch (choixSession) {
            case 1:
                gererLaSession(listFormations);
                break;
            case 2:
                vueAdmin.retour();
                gererSession();
                break;
        }
    }

    private void listInscriptionSession(Formation form) {
        listSessionFormation = model.getCentre().getListSessionByIdFormation(form.getIdFormation());
        vueAdmin.afficherListSession(listSessionFormation);

        vueAdmin.faireUnChoixValide();
        Session sess = new Session();
        int idSession = s.nextInt();
        sess = model.getForm().getSessionByIdSession(idSession);
        if (sess != null) {
            vueAdmin.afficherSession(sess);
            vueAdmin.print(sess.getListInscriptionBySession());
            gererSessionFormation(form);

        } else {
            vueAdmin.erreurSession();
            gererSessionFormation(form);
        }

    }

    private void editerSessionDeFormation(Formation form) {
        listSessionFormation = model.getCentre().getListSessionByIdFormation(form.getIdFormation());
        vueAdmin.afficherListSession(listSessionFormation);
        vueAdmin.faireUnChoixValide();
        Session sess = new Session();
        int idSession = s.nextInt();
        sess = model.getForm().getSessionByIdSession(idSession);
        if (sess != null) {
            vueAdmin.afficherSession(sess);
            editerSession(form, sess);
            gererSessionFormation(form);
        } else {
            vueAdmin.erreurSession();
            gererSessionFormation(form);
        }
    }

    private void editerSession(Formation form, Session sess) {
        int choixFormateur = 0;
        int choixlocal = 0;
        List<Formateur> listFormateurDispo = model.getCentre().getFormateurByFormation(form, sess);
        vueFormateur.afficheListFormateur(listFormateurDispo);
        if (listFormateurDispo.isEmpty()) {
            vueAdmin.aucunFormateur();
            ctrlFormation.gererSessionFormation(form);
        }
        vueAdmin.faireUnChoixValide();
        if (s.hasNextInt()) {
            choixFormateur = s.nextInt();
        } else {
            vueAdmin.erreurFormateur();
            editerSession(form, sess);
        }
        Formateur f = new Formateur();
        f = (Formateur) model.getCentre().getUserById(choixFormateur);
        if (f != null) {
            sess.setFormateur(f);
        } else {
            vueAdmin.erreurFormateur();
            ctrlFormation.gererSessionFormation(form);
        }
        s.nextLine();
        List<Local> listLocauxDispo = model.getCentre().getLocauxDispo(sess);
        vueAdmin.afficherListLocaux(listLocauxDispo);
        vueAdmin.faireUnChoixValide();
        if (s.hasNextInt()) {
            choixlocal = s.nextInt();
        } else {
            vueAdmin.erreurLocal();
            editerSession(form, sess);
        }
        Local local = new Local();
        local = model.getCentre().getLocalById(choixlocal);
        if (local != null) {
            sess.setLocal(local);
        } else {
            vueAdmin.erreurLocal();
            editerSession(form, sess);
        }
        sess.editerSession();
        gererSessionFormation(form);
    }

}
