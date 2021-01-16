package vue;

import java.util.List;
import model.Status;

public class VueLogin {

    public void entrerCoordonnee() {
        System.out.println("entrer les cooredonnées dans l'ordre suivant : " + "\n");
    }

    public void entrerNom() {
        System.out.println("Entrer votre nom : " + "\n");
    }

    public void entrerPrenom() {
        System.out.println("Entrer votre prenom : " + "\n");
    }

    public void entrerAdresse() {
        System.out.println("Entrer votre adresse : " + "\n");
    }

    public void entrerTelephone() {
        System.out.println("Entrer votre téléphone : " + "\n");
    }

    public void entrerEmail() {
        System.out.println("Entrer votre email : " + "\n");
    }

    public void entrerLogin() {
        System.out.println("Entrer votre login : " + "\n");
    }

    public void entrerPassword() {
        System.out.println("Entrer votre password : " + "\n");
    }

    public void entrerRole() {
        System.out.println("Entrer votre role : " + "\n");
    }

    public void entrerStatus() {
        System.out.println("Introduire l'ID de la formation : " + "\n");
    }

    public void login() {
        System.out.println("Entrez le login svp :  " + "\n");
    }

    public void password() {
        System.out.println("Entrez le password svp :  " + "\n");
    }

    public void erreur() {
        System.out.println("Erreur! Veuillez faire un choix valable : " + "\n");
        System.out.println("" + "\n");
    }

    public void afficheListStatus(List<Status> listStatus) {
        for (Status status : listStatus) {
            System.out.println("\n" + status.getIdStatus() + " - " + status.getNomStatus());
        }
    }

    public void erreurUser() {
        System.out.println("Erreur! Vous n'êtes pas inscrit, veuillez faire un choix valable : " + "\n");
        System.out.println("" + "\n");
    }

}
