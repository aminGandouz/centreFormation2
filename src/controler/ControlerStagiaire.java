package controler;

import static controler.ControlerUtils.controler;
import static controler.ControlerUtils.ctrlStagiaire;
import static controler.ControlerUtils.model;
import static controler.ControlerUtils.s;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Formation;
import model.Inscription;
import model.Session;
import model.Stagiaire;
import model.Status;
import model.Utilisateur;
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
    private Boolean emailExist = false;
    private Boolean loginExist = false;

    public void menusStagiaire(Stagiaire stagiaire) {
        vueStagiaire.presentationVueStagiaire();
        vueStagiaire.menuStagiaire();
        int choixDuMenus = choixMenus2(1, 6);
        switch (choixDuMenus) {
            case 1:
                inscriptionSession(stagiaire);
                break;

            case 2:
                annulerInscription(stagiaire);
                break;
            case 3:
                signalerPaiement(stagiaire);
                break;
            case 4:
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
                    Stagiaire stagiaire2 = (Stagiaire) controler.getUserConnecte();
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

    private void inscriptionSession(Stagiaire stagiaire) {
        s.nextLine();
        List<Formation> listFormation = model.getCentre().getListFormations();
        vAccueil.afficheList(listFormation);
        vueStagiaire.entrerIdFormation();
        if (s.hasNextInt()) {
            int idFormation = s.nextInt();
            Formation f = model.getCentre().getFormationById(idFormation);
            List<Session> listSession = model.getCentre().getListSessionByIdFormation(idFormation);
            if (listSession.isEmpty()) {
                vueStagiaire.pasDeSessionDispo();
                inscriptionSession(stagiaire);
            } else {
                vueStagiaire.afficheListSession(listSession);
                vueStagiaire.taperIdSession();
                s.nextLine();
                    checkInt();
                    Integer idSess = s.nextInt();
                    Session sess = model.getSess().getSessionByIdSession(idSess);
                    if (sess == null) {
                        inscriptionSession(stagiaire);
                    } else {
                        if (f.getMaxParticipant() > sess.getListInscription().size()) {
                            Boolean ajoutOK = model.getInscription().ajoutStagiaire(stagiaire, sess);
                            if (ajoutOK) {
                                vueStagiaire.ajoutOK();
                            } else {
                                vueStagiaire.plusDePlaceDispo();
                            }
                            menusStagiaire(stagiaire);
                        } else {
                            vueStagiaire.plusDePlaceDispo();
                            ctrlStagiaire.menusStagiaire(stagiaire);
                        }
                    }
         
            }
        } else {
            inscriptionSession(stagiaire);
        }
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
            do {
                vueLogin.entrerEmail();
                email = s.nextLine();
            } while (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                    || email == null
                    || email.trim().isEmpty());
        } while (model.getCentre().ifEmailExist(email) != null);
        stagiaire.setEmail(email);
        
        do {
            vueLogin.entrerLogin();
            login = s.nextLine();
        } while (login == null || login.trim().isEmpty() || model.getCentre().getUserByLogin(login) != null);
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
            do {
                vueLogin.entrerEmail();
                email = s.nextLine();
            } while (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                    || email == null
                    || email.trim().isEmpty());
            if (model.getCentre().ifEmailExist(email) != null) {
                Utilisateur user = model.getCentre().ifEmailExist(email);
                if (user.getIdUtilisateur() == stagiaire2.getIdUtilisateur()) {
                    emailExist = true;
                }
            } else {
                emailExist = true;
            }
        } while (!emailExist);
        stagiaire2.setEmail(email);
        
        do {
            do {
                vueLogin.entrerLogin();
                login = s.nextLine();
            } while (login == null || login.trim().isEmpty());
            if (model.getCentre().getUserByLogin(login) != null) {
                Utilisateur user = model.getCentre().getUserByLogin(login);
                if (user.getIdUtilisateur() == stagiaire2.getIdUtilisateur()) {
                    loginExist = true;
                }
            } else {
                loginExist = true;
            }
        } while (!loginExist);

        stagiaire2.setLogin(login);

        do {
            vueLogin.entrerPassword();
            password = s.nextLine();
        } while (password
                == null || password.trim()
                        .isEmpty());
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
        Collections.sort(listStatus, Status.ComparatorId);
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

    private void annulerInscription(Stagiaire stagiaire) {
        List<Inscription> listInscription = stagiaire.getListInscription();
        if (listInscription.isEmpty()) {
            vueStagiaire.listDesInscriptionsVide();
            menusStagiaire(stagiaire);
        } else {
            vueStagiaire.afficheList(listInscription);
            vueStagiaire.faireChoix();
            if (s.hasNextInt()) {
                int choixInscription = s.nextInt();
                Inscription inscription = model.getStagiaire().getInscritpionById(choixInscription);
                if (inscription == null) {
                    vueStagiaire.faireChoix();
                    menusStagiaire(stagiaire);
                } else {
                    Boolean deleteOk = stagiaire.annulerInscription(inscription);
                    if (deleteOk) {
                        vueStagiaire.deleteOK();
                    } else {
                        vueStagiaire.deleteNotOK();
                    }
                    menusStagiaire(stagiaire);
                }
            } else {
                menusStagiaire(stagiaire);
            }
        }

    }

    private void signalerPaiement(Stagiaire stagiaire) {
        List<Inscription> listInscription = stagiaire.getListInscriptionNonPayer();
        if (listInscription.isEmpty()) {
            vueStagiaire.listDesInscriptionsVide();
            menusStagiaire(stagiaire);
        } else {
            vueStagiaire.afficheList(listInscription);
            vueStagiaire.faireChoix();
            if (s.hasNextInt()) {
                int choixInscription = s.nextInt();
                Inscription inscription = model.getStagiaire().getInscritpionById(choixInscription);
                if (inscription == null) {
                    vueStagiaire.faireChoix();
                    menusStagiaire(stagiaire);
                } else {
                    Boolean paiementOk = stagiaire.signalerPaiement(inscription);
                    if (paiementOk) {
                        vueStagiaire.paiementOK();
                    } else {
                        vueStagiaire.paiementNotOK();
                    }
                    menusStagiaire(stagiaire);
                }
            } else {
                menusStagiaire(stagiaire);
            }
        }
    }
}
