package vue;

import java.util.List;
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
    
    public void erreur() {
        System.out.println("Erreur! Veuillez faire un choix valable : " + "\n");
        System.out.println("" + "\n");
    }

    public void listDesInscriptions() {
        System.out.println("Voici la list de vos inscritpion(s) : ");
    }

    public void afficheList(List<Inscription> listInscription) {
        for (Inscription inscription : listInscription) {
            String p = inscription.getEstPaye() == true ? " Vous avez déja payé ": " Vous devez payer la somme de " +inscription.getPrix();
            System.out.println(" IdInscription : " + inscription.getIdInscription() + "\n"  + p);
        }
    }

    public void afficheListSession(List<Session> listSession) {
        for (Session session : listSession) {
            System.out.println(" Id de la session : " + session.getIdSession() +"\n" + " id du formateur  :  " + session.getFormateur().getNom() + "\n" + " Date du début de la formation : " + session.getDateDebut() + "\n" + " Date de fin : " + session.getDateFin() + "\n" + " Local : " + session.getLocal().getNomLocal() + "\n");
        }
    }

    public void inscriptionSession() {
        System.out.println("Vueillez rechercher une session : " + "\n");
    }

    public void taperNomFormation() {
        System.out.println("\n" +"Entrer le nom de la formation : " + "\n");
    }

    public void taperIdSession() {
        System.out.println("\n" +"Introduire l'id de la session : " + "\n");
    }

    public void notSubscribe() {
        System.out.println("\n" + "Vous n'êtes inscrit à aucune session !!!!!!! " + "\n");
    }

    public void pasDeSessionDispo() {
        System.out.println("\n" + " Il n'y a pas de session disponible pour cette formation " + "\n");
    }

    public void entrerIdFormation() {
         System.out.println("\n" + " Entrez l'id de la formation " + "\n");
    }

    public void plusDePlaceDispo() {
        System.out.println("\n" + " Il n'y a plus de place disponible pour cette session ou vous y êtes déja inscrit  " + "\n");
    }

    public void ajoutOK() {
         System.out.println("\n" + " Vous êtes bien inscrit a la session choisit  " + "\n");
    }

    public void faireChoix() {
         System.out.println("\n" + " Faites un choix valide  " + "\n");
    }

    public void listDesInscriptionsVide() {
        System.out.println("\n" + " Vous n'êtes inscrit à aucune session vous êtes redirigé vers le menu   " + "\n");
    }

    public void deleteOK() {
         System.out.println("\n" + " Vous avez bien annuler votre inscription  " + "\n");
    }

    public void deleteNotOK() {
       System.out.println("\n" + " L'annulation de votre inscription ne s'est pas fait , contactez le centre de formation   " + "\n");
    }

    public void paiementOK() {
        System.out.println("\n" + " La signalisation de votre paiement a bien été effectué   " + "\n");
    }

    public void paiementNotOK() {
        System.out.println("\n" + " La signalisation de votre paiement ne s'est pas fait , contactez le centre de formation   " + "\n");
    }
}
