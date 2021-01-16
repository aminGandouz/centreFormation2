package vue;

import java.util.List;
import model.Formation;

public class Accueil {

    public void presentation() {
        System.out.println("Accueil Centre de Formation " + "\n");
    }

    public void choix() {
        System.out.println("Veuillez faire un choix svp: " + "\n");
        System.out.println("1 -> Authentification" + "\n");
        System.out.println("2 -> Catalogue" + "\n");
        System.out.println("3 -> Inscription" + "\n");
        System.out.println("4 -> Quitter" + "\n");
    }

    public void erreur() {
        System.out.println("Erreur! Veuillez faire un choix valable : " + "\n");
        System.out.println("" + "\n");
    }

    public void inscription() {
        System.out.println("veuillez entrer vos coordonnées svp : " + "\n");

    }

    public void afficherLeCatalogue() {
        System.out.println("Voici le catalogue au complets : " + "\n");
    }

    public void choixDuCatalogue() {
        System.out.println("1 -> Rechercher" + "\n");
        System.out.println("2 -> Afficher le catalogue au complet " + "\n");
        System.out.println("3 -> Retour" + "\n");
    }

    public void afficheList(List<Formation> listFormation) {
        for (Formation formation : listFormation) {
            System.out.println("Intitulé : " + formation.getIntitule() + " Prix :  " + formation.getPrix() + " Durée :  " + formation.getDuree());
        }
    }

    public void rechercheCatalogue() {
        System.out.println("Entrer le nom de la formation svp : ");
    }

}
