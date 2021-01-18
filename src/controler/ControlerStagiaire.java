package controler;

import static controler.ControlerUtils.controler;
import static controler.ControlerUtils.ctrlStagiaire;
import static controler.ControlerUtils.model;
import static controler.ControlerUtils.s;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Formation;
import model.Inscription;
import model.Session;
import model.Stagiaire;
import model.Status;
import org.mindrot.jbcrypt.BCrypt;
import vue.Accueil;
import vue.VueLogin;
import vue.VueStagiaire;

public class ControlerStagiaire implements ControlerUtils {

    private final VueStagiaire vueStagiaire = new VueStagiaire();
    private final VueLogin vueLogin = new VueLogin();
    private final Accueil vAccueil = new Accueil();
    private String nom = null, prenom = null, adresse = null, telephone = null, email = null, login = null, password = null, role = "stagiaire", status = null;
    private Stagiaire stagiaire = new Stagiaire();

    public void menusStagiaire(Stagiaire stagiaire) {
        vueStagiaire.presentationVueStagiaire();
        vueStagiaire.menuStagiaire();
        int choixDuMenus = choixMenus2(1, 6);
        switch (choixDuMenus) {
            case 1:
                inscriptionSession();
                break;

            case 2:
                System.out.println("annuler");
                break;
            case 3:
                System.out.println("signaler");               
                break;
            case 4:
                System.out.println("consulter");
                vueStagiaire.listDesInscriptions();
                List<Inscription> listInscription = stagiaire.getListDesInscriptions();
                if (listInscription.isEmpty()) {
                    vueStagiaire.notSubscribe();
                    menusStagiaire(stagiaire);
                } else {
                    vueStagiaire.afficheList(listInscription);
                    menusStagiaire(stagiaire);
                }
                break;
            case 5:
                if (controler.getUserConnecte() instanceof Stagiaire) {
                    Stagiaire stagiaire2 = (Stagiaire)controler.getUserConnecte();
                    ctrlStagiaire.updateStagiaire(stagiaire2);
                    menusStagiaire(stagiaire);
                }
                break;
            case 6:
                vueStagiaire.deconnecte();
                controler.setUserConnecte(null);
                break;
        }
    }

    public int getSessionId(List<Session> listSession) {
        int choixDuMenus = 0;
        Map<Integer, String> mapStatus = new HashMap<>();
//        for (Session session : listSession) {
//            mapStatus.put(session.getIdSession(), session.getFormation().getIntitule());
//        }
        if (s.hasNextInt()) {
            choixDuMenus = s.nextInt();
            if (mapStatus.containsKey(choixDuMenus)) {
                return choixDuMenus;
            }
        }
        s.nextLine();
        getSessionId(listSession);
        return choixDuMenus;
    }

    private void inscriptionSession() {
        s.nextLine();
        List<Formation> listFormation = model.getCentre().getListFormations();
        vAccueil.afficheList(listFormation);
        vueStagiaire.taperNomFormation();
        String nameFormation = s.nextLine();// Ajouter une condition 
        // formation 
        List<Session> listSession = model.getCentre().getListSessionByName(nameFormation);
        vueStagiaire.afficheListSession(listSession);
        vueStagiaire.taperIdSession();

        Integer idsession = getSessionId(listSession);
        // inscription dans session 
        model.getInscription().ajoutStagiaire(stagiaire.getIdUtilisateur(), idsession);
        menusStagiaire(stagiaire);
    }
    
    
    public void inscription() {
        vAccueil.inscription();
        s.nextLine();
        do {
            vueLogin.entrerNom();
            nom = s.nextLine();
        } while (nom == null || nom.trim().isEmpty());
        stagiaire.setNom(nom);
        do {
            vueLogin.entrerPrenom();
            prenom = s.nextLine();
        } while (prenom == null || prenom.trim().isEmpty());
        stagiaire.setPrenom(prenom);
        do {
            vueLogin.entrerAdresse();
            adresse = s.nextLine();
        } while (adresse == null || adresse.trim().isEmpty());
        stagiaire.setAdresse(adresse);
        do {
            vueLogin.entrerTelephone();
            telephone = s.nextLine();
        } while (telephone == null || telephone.trim().isEmpty());
        stagiaire.setTelephone(telephone);
        do {
            vueLogin.entrerEmail();
            email = s.nextLine();
        } while (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") || email == null || email.trim().isEmpty());
        //"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        //"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
        stagiaire.setEmail(email);
        do {
            vueLogin.entrerLogin();
            login = s.nextLine();
        } while (login == null || login.trim().isEmpty());
        stagiaire.setLogin(login);
        do {
            vueLogin.entrerPassword();
            password = s.nextLine();
        } while (password == null || password.trim().isEmpty());
        String hp = BCrypt.hashpw(password, BCrypt.gensalt());
        stagiaire.setPassword(hp);

        int idStatus = getStatusId();
        Status status = new Status();
        status.setIdStatus(idStatus);
        stagiaire.setStatus(status);
        stagiaire.ajoutStagiaire();
        controler.start();
    }

    public void updateStagiaire(Stagiaire stagiaire2) {
        vueLogin.entrerCoordonnee();
        s.nextLine();

        do {
            vueLogin.entrerNom();
            nom = s.nextLine();
        } while (nom == null || nom.trim().isEmpty());
        stagiaire2.setNom(nom);
        do {
            vueLogin.entrerPrenom();
            prenom = s.nextLine();
        } while (prenom == null || prenom.trim().isEmpty());
        stagiaire2.setPrenom(prenom);
        do {
            vueLogin.entrerAdresse();
            adresse = s.nextLine();
        } while (adresse == null || adresse.trim().isEmpty());
        stagiaire2.setAdresse(adresse);
        do {
            vueLogin.entrerTelephone();
            telephone = s.nextLine();
        } while (telephone == null || telephone.trim().isEmpty());
        stagiaire2.setTelephone(telephone);
        do {
            vueLogin.entrerEmail();
            email = s.nextLine();
        } while (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") || email == null || email.trim().isEmpty());
        //"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        //"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
        stagiaire2.setEmail(email);
        do {
            vueLogin.entrerLogin();
            login = s.nextLine();
        } while (login == null || login.trim().isEmpty());
        stagiaire2.setLogin(login);
        do {
            vueLogin.entrerPassword();
            password = s.nextLine();
        } while (password == null || password.trim().isEmpty());
        String hp = BCrypt.hashpw(password, BCrypt.gensalt());
        stagiaire2.setPassword(hp);

        int idStatus = getStatusId();
        Status status = new Status();
        status.setIdStatus(idStatus);
        stagiaire2.setStatus(status);
        stagiaire2.updateStagiaire();
    }

    public int getStatusId() {
        int choixDuMenus = 0;
        Map<Integer, String> mapStatus = new HashMap<>();
        List<Status> listStatus = model.getCentre().getAllStatus();
        for (Status status : listStatus) {
            mapStatus.put(status.getIdStatus(), status.getNomStatus());
        }
        vueLogin.afficheListStatus(listStatus);
        vueLogin.entrerStatus();
        if (s.hasNextInt()) {
            choixDuMenus = s.nextInt();
            if (mapStatus.containsKey(choixDuMenus)) {
                return choixDuMenus;
            }
        }
        s.nextLine();
        getStatusId();
        return choixDuMenus;
    }

    @Override
    public void erreur() {
        vueStagiaire.erreur();
    }

}
