package controler;

import static controler.ControlerUtils.ctrlFormation;
import static controler.ControlerUtils.model;
import static controler.ControlerUtils.s;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Formateur;
import model.Formation;
import model.Session;
import vue.VueAdmin;
import vue.VueLogin;

public class ControlerAdmin implements ControlerUtils {

    /**
     * ***** Champs Attribut ******
     */
    private final VueAdmin vueAdmin = new VueAdmin();
    private final VueLogin vueLogin = new VueLogin();
    private List<Formateur> listFormateurs = new ArrayList<>();
    private String nom = null, prenom = null, adresse = null, telephone = null, email = null, login = null, password = null;
    private Integer prix = null, duree = null, maxParticipant = null;

    /**
     * ***** All methods ******
     */
    public void menuAdmin(Admin admin) {
        vueAdmin.presentationVueAdmin();
        vueAdmin.menuAdmin();
        int choixDuMenus = choixMenus2(1, 7);

        switch (choixDuMenus) {
            case 1:
                System.out.println("gerer formation");
                ctrlFormation.gererFormation();
                menuAdmin(admin);
                break;
            case 2:
                System.out.println("gerer session");
                ctrlFormation.gererSession();
                menuAdmin(admin);
                break;
            case 3:
                System.out.println("gerer formateurs");
                gererFormateur(admin);
                menuAdmin(admin);
                break;
            case 4:
                System.out.println("rechercher le formateur d'une session");
                ctrlFormation.rechercherSessionFormateur(admin);
                menuAdmin(admin);
                break;
            case 5:
                System.out.println("Communiquer Formateur Prestations ");
                CommuniquerFormateurPrestations();
                menuAdmin(admin);
                break;
            case 6:
                System.out.println("Sortir pour chaque formation la liste des sessions planifiées et le nombre de places encore disponibles");
                displayAllSessionByFormation();
                menuAdmin(admin);
                //// !!!!!!!!!!!!!!!! A Finir  !!!!!!!!!!!!!!!! ///////
                break;
            case 7:
                System.exit(0);
                break;
        }

    }

    private void gererFormateur(Admin admin) {
        vueAdmin.menusGererFormateur();
        int choixDuMenus = choixMenus(1, 7);

        switch (choixDuMenus) {
            case 1:
                vueAdmin.ajouterUnFormateur();
                ajoutFormateur();
                gererFormateur(admin);
            case 2:
                vueAdmin.effacerFormateur();
                effacerFormateur();
                gererFormateur(admin);
                break;
            case 3:
                vueAdmin.updateFormateur();
                listFormateurs = model.getCentre().getListFormateurs();
                vueAdmin.afficherFormateurs(listFormateurs);
                updateFormateur();
                gererFormateur(admin);
                break;
            case 4:
                vueAdmin.afficherLesFormateurs();
                List<Formateur> listFormateurs2 = model.getCentre().getListFormateurs();
                vueAdmin.afficherFormateurs(listFormateurs2);
                gererFormateur(admin);
                break;
            case 5:
                ajoutFormationAuFormateur(admin);
                break;
            case 6:
                deleteFormationDuFormateur(admin);
                break;
            case 7:
                menuAdmin(admin);
                break;
        }
    }

    private void ajoutFormateur() {

        s.nextLine();
        Formateur formateur = new Formateur();

        do {
            vueAdmin.entrerNom();
            nom = s.nextLine();
        } while (nom == null || nom.trim().isEmpty());// trim retire les espaces avt et apres et Si ce qui reste est vide => le string de base n'avait que des espaces 
        formateur.setNom(nom);

        do {
            vueAdmin.entrerPrenom();
            prenom = s.nextLine();
        } while (prenom == null || prenom.trim().isEmpty());
        formateur.setPrenom(prenom);

        do {
            vueAdmin.entrerAdresse();
            adresse = s.nextLine();
        } while (adresse == null || adresse.trim().isEmpty());
        formateur.setAdresse(adresse);

        do {
            vueAdmin.enterTelephone();
            telephone = s.nextLine();
        } while (telephone == null || telephone.trim().isEmpty());
        formateur.setTelephone(telephone);

        do {
            vueLogin.entrerEmail();
            email = s.nextLine();
        } while (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                || email == null
                || email.trim().isEmpty());
        formateur.setEmail(email);

        do {
            vueLogin.entrerLogin();
            login = s.nextLine();
        } while (login == null || login.trim().isEmpty());
        formateur.setLogin(login);

        do {
            vueLogin.entrerPassword();
            password = s.nextLine();
        } while (password == null || password.trim().isEmpty());
        formateur.setPassword(password);
        formateur.ajoutFormateur();
    }

    private void effacerFormateur() {
        vueAdmin.effacerFormateur();
        Formateur f = null;
        String formateur = null;
        s.nextLine();

        do {
            formateur = s.nextLine();
        } while (formateur == null || formateur.trim().isEmpty());
        Boolean formateurExist = model.getCentre().isFormateurExist(formateur);
        if (!formateurExist) {
            System.out.println("Le formateur n'existe pas ");
            effacerFormateur();
        } else {
            f.effacerFormateur();
        }
    }

    private void updateFormateur() {
        s.nextLine();
        String nomDuFormateur;

        do {
            vueAdmin.entrerNom();
            nomDuFormateur = s.nextLine();
        } while (nomDuFormateur == null || nomDuFormateur.trim().isEmpty());
        Formateur formateur = model.getCentre().getFormateurAvecNom(nomDuFormateur);

        if (formateur == null) {
            updateFormateur();
        } else {
            s.nextLine();

            do {
                vueLogin.entrerNom();
                nom = s.nextLine();
            } while (nom == null || nom.trim().isEmpty());
            formateur.setNom(nom);

            do {
                vueLogin.entrerPrenom();
                prenom = s.nextLine();
            } while (prenom == null || prenom.trim().isEmpty());
            formateur.setPrenom(prenom);

            do {
                vueLogin.entrerAdresse();
                adresse = s.nextLine();
            } while (adresse == null || adresse.trim().isEmpty());
            formateur.setAdresse(adresse);

            do {
                vueLogin.entrerTelephone();
                telephone = s.nextLine();
            } while (telephone == null || telephone.trim().isEmpty());
            formateur.setTelephone(telephone);

            do {
                vueLogin.entrerEmail();
                email = s.nextLine();
            } while (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                    || email == null
                    || email.trim().isEmpty());
            formateur.setEmail(email);

            do {
                vueLogin.entrerLogin();
                login = s.nextLine();
            } while (login == null || login.trim().isEmpty());
            formateur.setLogin(login);

            do {
                vueLogin.entrerPassword();
                password = s.nextLine();
            } while (password == null || password.trim().isEmpty());
            formateur.setPassword(password);
            formateur.updateFormateur();
        }
    }

    /**
     * ***** Errors method ******
     */
    @Override
    public void erreur() {
        vueAdmin.erreur();
    }

    private void CommuniquerFormateurPrestations() {
        s.nextLine();
        String nomFormateur;
        List<Session> listSessionFormateur = new ArrayList<>();
        vueAdmin.entrerNom();
        // do {               
        nomFormateur = s.nextLine();
        listSessionFormateur = model.getCentre().getListSessionByNameFormateur(nomFormateur);
//                    s.nextLine();
//                    if(listSessionFormateur.isEmpty()){
//                        vueAdmin.formateurInconnu(nomFormateur);
//                    }
//                    
//                } while (listFormateurs.isEmpty());
        vueAdmin.listDesFormateurs();
        vueAdmin.afficherListSessionFormateur(listSessionFormateur);

    }

    private void displayAllSessionByFormation() {
        List<Formation> listForm = model.getCentre().getListFormations();
        vueAdmin.displayAllSessionByFormation(listForm);
        /////////// !!!!!!!!!!!!! gérer le compte du nbre de participants 

    }

    private void ajoutFormationAuFormateur(Admin admin) {
        vueAdmin.ajouterFormationAuFormateur();
        List<Formateur> listFormateurs3 = model.getCentre().getListFormateurs();
        vueAdmin.afficherFormateurs(listFormateurs3);
        vueAdmin.faireUnChoixValide();
        int idFormateur = s.nextInt();
        Formateur f = (Formateur) model.getCentre().getUserById(idFormateur);
        if (null == f) {
            vueAdmin.erreur();
            ajoutFormationAuFormateur(admin);
        } else {
            List<Formation> listFormationDispoPOurFormateur = model.getCentre().getListFormationDispoPOurFormateur(f.getIdUtilisateur());
            vueAdmin.afficherFormations(listFormationDispoPOurFormateur);
            if (listFormationDispoPOurFormateur.isEmpty()) {
                vueAdmin.listFormationVide();
            }
            int choixDeLaFormation = s.nextInt();
            Formation existe = model.getCentre().getFormationById(choixDeLaFormation);
            if (null == existe) {
                ajoutFormationAuFormateur(admin);
            } else {
                model.getCentre().ajoutFormationAuFormateur(f.getIdUtilisateur(), existe.getIdFormation());
                gererFormateur(admin);
            }

        }
    }

    private void deleteFormationDuFormateur(Admin admin) {
        vueAdmin.deleteFormationDuFormateur();
        List<Formateur> listFormateurs3 = model.getCentre().getListFormateurs();
        vueAdmin.afficherFormateurs(listFormateurs3);
        vueAdmin.faireUnChoixValide();
        int idFormateur = s.nextInt();
        Formateur f = (Formateur) model.getCentre().getUserById(idFormateur);
        if (f == null) {
            vueAdmin.erreur();
            deleteFormationDuFormateur(admin);
        } else {
            vueAdmin.choixDeLaFormation();
            List<Formation> listFormationDuFormateur = model.getCentre().getListDuFormateur(f.getIdUtilisateur());
            vueAdmin.afficherFormations(listFormationDuFormateur);
            if (listFormationDuFormateur.isEmpty()) {
                vueAdmin.listFormationVidePourFormateur();
            }
            int choixDeLaFormation = s.nextInt();
            Formation existe = model.getCentre().getFormationById(choixDeLaFormation);
            if (existe == null) {
                ajoutFormationAuFormateur(admin);
            } else {
                model.getCentre().deleteFormationDuFormateur(f.getIdUtilisateur(), existe.getIdFormation());
                gererFormateur(admin);
            }

        }
    }

}

//        while (!prixString.matches("\\d+") || Integer.parseInt(prixString) <= 0);
//        prix = Integer.parseInt(prixString);
//"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
//"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"