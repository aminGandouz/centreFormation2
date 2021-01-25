package controler;

import static controler.ControlerUtils.controler;
import static controler.ControlerUtils.model;
import static controler.ControlerUtils.s;
import java.util.ArrayList;
import java.util.List;
import model.*;
import model.dao.AbstractDaoFactory;
import model.dao.mySqlDao.MySqlDaoFactory;
import vue.Accueil;

public class Controler implements ControlerUtils {

    /**
     * ***** Champs Attribut ******
     */
    private Utilisateur userConnecte = null;
    private final Accueil vAccueil = new Accueil();

    /**
     * ***** Constructor ******
     */
    public Controler() {
        AbstractDaoFactory.setFactory(MySqlDaoFactory.getInstance());
        cleanDB();
    }

    /**
     * ***** Getters and Setters ******
     */
    public Utilisateur getUserConnecte() {
        return userConnecte;
    }

    public void setUserConnecte(Utilisateur userConnecte) {
        this.userConnecte = userConnecte;
    }

    /**
     * ***** Methods ******
     */
    public void start() {
        vAccueil.presentation();
        vAccueil.choix();
        int choice = choixMenus2(1, 4);

        switch (choice) {
            case 1:
                ctrlLogin.authentification();
                break;
            case 2:
                choixCatalogue();
                break;
            case 3:
                ctrlStagiaire.inscription();
                break;

            case 4:
                System.exit(0);
                break;
        }
    }

    public void choixCatalogue() {
        String nomFormation = null;
        vAccueil.choixDuCatalogue();
        int choixCataogue = choixMenus2(1, 3);
        List<Formation> listFormationParNom = new ArrayList<>();

        switch (choixCataogue) {
            case 1:
                do {
                    do {
                        vAccueil.rechercheCatalogue();
                        nomFormation = s.next();
                    } while (nomFormation == null || nomFormation.trim().isEmpty());
                    listFormationParNom = model.getCentre().getListFormationsByNameFormation(nomFormation);
                } while (listFormationParNom.isEmpty() || listFormationParNom == null);
                vAccueil.afficheList(listFormationParNom);
                choixCatalogue();
                break;
            case 2:
                vAccueil.afficherLeCatalogue();
                List<Formation> listFormation = model.getCentre().getListFormations();
                vAccueil.afficheList(listFormation);
                choixCatalogue();
                break;
            case 3:
                controler.start();
                break;
        }
    }

    private void cleanDB() {
        model.getCentre().cleanDB();
    }

    /**
     * ***** Error method ******
     */
    @Override
    public void erreur() {
        vAccueil.erreur();
    }
}
