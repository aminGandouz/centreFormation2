package vue;

import java.util.List;
import model.Formation;
import model.Inscription;
import model.Session;

public class VueStagiaire {

    public void presentationVueStagiaire() {
        System.out.println("Bienvenue dans le centre de formation : " + "\n");
    }

    public void deconnecte() {
        System.out.println(" se deconnecter : ");
    }

    public void menuStagiaire() {
        System.out.println("Faites un choix svp: " + "\n");
        System.out.println("1 -> S’inscrire à une session donnée d’une formation donnée" + "\n");
        System.out.println("2 -> Annuler une inscription" + "\n");
        System.out.println("3 -> Signaler le payement d’une inscription" + "\n");
        System.out.println("4 -> Consulter la liste de ses inscriptions" + "\n");
        System.out.println("5 -> Modifier ses informations personnelles" + "\n");
        System.out.println("6 -> Se déconnecter" + "\n");
    }

//    public void erreur() {
//        System.out.println("Erreur! Veuillez faire un choix entre 1 et 4 svp : " + "\n");
//        System.out.println("" + "\n");
//        menuStagiaire();
//    }
    
    public void erreur() {
        System.out.println("Erreur! Veuillez faire un choix valable : " + "\n");
        System.out.println("" + "\n");
    }



    public void listDesInscriptions() {
        System.out.println("Voici la list de vos inscritpion(s) : ");
    }

    public void afficheList(List<Inscription> listInscription) {
        for (Inscription inscription : listInscription) {
            System.out.println(" IdInscription : " + inscription.getStagiaire().getIdUtilisateur() + "\n"  + " Cotisation payé : " + inscription.getEstPaye() + "\n");
        }
    }

    public void afficheListSession(List<Session> listSession) {
        for (Session session : listSession) {
            System.out.println(" Id de la session : " + session.getIdSession() + "\n"  + "\n" + " id du formateur  :  " + session.getFormateur().getNom() + "\n" + " Date du début de la formation : " + session.getDateDebut() + "\n" + " Date de fin : " + session.getDateFin() + "\n" + " Local : " + session.getLocal().getNomLocal() + "\n");
        }
    }

    public void inscriptionSession() {
        System.out.println("Vueillez rechercher une session : " + "\n");
    }

    public void taperNomFormation() {
        System.out.println("Entrer le nom de la formation : " + "\n");
    }

    public void taperIdSession() {
        System.out.println("Introduire l'id de la session : " + "\n");
    }

    public void notSubscribe() {
        System.out.println("\n" + "Vous n'êtes inscrit à aucune session !!!!!!! " + "\n");
    }

}
