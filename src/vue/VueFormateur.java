package vue;

import java.util.List;
import model.Formateur;
import model.Session;

public class VueFormateur {

    public static void FormateurNotExist() {
        System.out.println("\n" + "Le formateur n'existe pas " + "\n");
    }

    public void menuFormateur() {
        System.out.println("\n" + " Faites votre chois : " + "\n");
        System.out.println("\n" + " 1 -> Afficher la liste de mes formations " + "\n");
        System.out.println("\n" + " 2 -> Quitter la session " + "\n");
    }

    public void afficheListSessionNomFormateur(List<Session> listInscription) {
        if (listInscription.isEmpty()) {
            System.out.println("Vous ne donnez pas cours pour le moment ");
        } else {
            for (Session session : listInscription) {
                System.out.println("\n" + "Formation numéros : " + session.getIdSession()
                        + "\n" + "Nom du formateur : " + session.getFormateur().getNom()
                        + "\n" + "Date du début de votre formation : " + session.getDateDebut()
                        + "\n" + "Date de la fin de votre formation : " + session.getDateFin()
                        + "\n" + "Nom du local de votre formation : " + session.getLocal().getNomLocal());
            }
        }

    }

    public void erreur() {
        System.out.println("Erreur! Veuillez faire un choix valable : " + "\n");
        System.out.println("" + "\n");
    }

    public void afficheListFormateur(List<Formateur> listFormateur) {
        if (listFormateur.isEmpty()) {
            System.out.println("Il n'y a pas de formateur ");
        } else {
            for (Formateur formateur : listFormateur) {
                System.out.println("\n" + " Formateur  ID : " + formateur.getIdUtilisateur()
                        + "\n" + " Nom du formateur : : " + formateur.getNom()
                        + "\n" + " Prénom du formateur : " + formateur.getPrenom()
                        + "\n" + " Rôle du formateur : " + formateur.getRole()
                        + "\n");
            }
        }
    }
}
