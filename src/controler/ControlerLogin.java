package controler;

import model.Admin;
import model.Formateur;
import model.Stagiaire;
import model.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import vue.VueAdmin;
import vue.VueLogin;
import vue.VueStagiaire;

public class ControlerLogin implements ControlerUtils {

    private final VueLogin vueLogin = new VueLogin();
    private final VueAdmin vueAdmin = new VueAdmin();
    private final VueStagiaire vueStagiaire = new VueStagiaire();
    private String login = null, password = null;

    @SuppressWarnings("null")
    public void authentification() {
        do {
            vueLogin.login();
            login = s.next();
            if ((login != null) || !(login.trim().isEmpty())) {
                do {
                    vueLogin.password();
                    password = s.next();
                } while (password == null || password.trim().isEmpty());
            }
        } while (login == null || login.trim().isEmpty());

        //controler.setUserConnecte(Utilisateur.authentification(login, password));
        controler.setUserConnecte(Utilisateur.authentificationLogin(login));
        if (controler.getUserConnecte() == null) {
            vueLogin.erreurUser();
            controler.start();
        } else {
            if (BCrypt.checkpw(password, controler.getUserConnecte().getPassword())) {
                if (controler.getUserConnecte() instanceof Stagiaire) {
                    Stagiaire stagiaire = (Stagiaire) Utilisateur.authentification(login, password);
                    ctrlStagiaire.menusStagiaire(stagiaire);
                } else if (controler.getUserConnecte() instanceof Admin) {
                    Admin admin = (Admin) Utilisateur.authentification(login, password);
                    ctrlAdmin.menuAdmin(admin);
                } else if (controler.getUserConnecte() instanceof Formateur) {
                    Formateur formateur = (Formateur) Utilisateur.authentification(login, password);
                    ctrlFormateur.menuFormateur(formateur);
                }
            } else {
                vueLogin.erreurUser();
                controler.start();
            }
        }

        if (controler.getUserConnecte() == null) {
            vueLogin.erreurUser();
            controler.start();
        }

    }

    @Override
    public void erreur() {
        vueAdmin.erreur();
    }
}
