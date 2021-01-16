package vue;

import java.util.List;
import model.Formateur;
import model.Formation;
import model.Local;
import model.Session;

public class VueAdmin {

    /**
     * ***** Presentation ******
     */
    public void presentationVueAdmin() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Bienvenue dans le centre de formation Mr l'administrateur : " + "\n");
    }

    /**
     * ***** Admin ******
     */
    public void menuAdmin() {
       System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Faites un choix svp: " + "\n");
        System.out.println("1 -> Gérer les formations " + "\n");
        System.out.println("2 -> Gérer les sessions de formation" + "\n");
        System.out.println("3 -> Gérer les formateurs" + "\n");
        System.out.println("4 -> Rechercher le formateur d’une session" + "\n");
        System.out.println("5 -> Communiquer à chaque formateur les prestations qu’il doit assurer (jour, local, formation)" + "\n");
        System.out.println("6 -> Sortir pour chaque formation la liste des sessions planifiées et le nombre de places encore disponibles " + "\n");
        System.out.println("7 -> Retour " + "\n");
    }

    public void erreur() {
        System.out.println("Erreur! Veuillez faire un choix valide svp  " + "\n");
        System.out.println("" + "\n");
        //menuAdmin();
    }

    /**
     * ***** Formation ******
     */
    public void menuGererFormation() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n" + "BIENVENUE DANS LA GESTION DE FORMATIONS : " + "\n");
        System.out.println("\n" + "Faites un choix svp: " + "\n");
        System.out.println("\n" + "1 -> Créer une formation " + "\n");
        System.out.println("\n" + "2 -> Annuler une formation " + "\n");
        System.out.println("\n" + "3 -> Update une formation " + "\n");
        System.out.println("\n" + "4 -> Afficher une formation " + "\n");
        System.out.println("\n" + "5 -> Retour" + "\n");
    }

    public void ajouterUneFormation() {
        System.out.println("\n" + "Vous pouvez ajouter une formation : " + "\n");
    }

    public void entrerNomFormation() {
        System.out.println("\n" + "Entrer l'intitulé de la formation : " + "\n");// prix duree maxPar
    }

    public void entrerLePrixDeLAFormation() {
        System.out.println("\n" + "Entrer le prix de la formation : " + "\n");
    }

    public void enterLaDuréeDeLaFormation() {
        System.out.println("\n" + "Entrer la durée de la formation en jours : " + "\n");
    }

    public void enterMaxParticipant() {
        System.out.println("\n" + "Entrer le nombre maximum de particpant : " + "\n");
    }

    public void effaceFormation() {
        System.out.println("\n" + "Taper le nom de la formation a effacer : " + "\n");
    }

    public void afficherLesFormations() {
        System.out.println("\n" + "Voici le liste des formation : " + "\n");
    }

    public void afficherFormations(List<Formation> listFormations) {
        listFormations.forEach(form -> {
            System.out.println("\n" + " Id de la formation " + form.getIdFormation() + "\n" + " Intitulé : " + form.getIntitule() + "\n" + " Durée en jours  : " + form.getDuree() + " jours " + "\n" + " Prix : " + form.getPrix() + " euros " + "\n" + " Nombre de participant maximum : " + form.getMaxParticipant() + "\n");
        });
    }

    public void updateFormation() {
        System.out.println("\n" + " List des formations : " + "\n");
    }

    public void entrerCoordonnee() {
        System.out.println("Entrer l'intitulé de la formation  : " + "\n");
    }

    public void afficherLaFormation(Formation form) {
        System.out.println("\n" + "Id de la formation :" + form.getIdFormation() + "\n" + "Intitulé : " + form.getIntitule() + "\n" + "Prix : " + form.getPrix() + "\n" + "Durée :  " + form.getDuree() + "\n" + "Max participant :  " + form.getMaxParticipant() + "\n");//To change body of generated methods, choose Tools | Templates.
    }

    public void erreurFormation() {
        System.out.println("\n" + "Cette formation n'existe pas : " + "\n");
    }

    public void deleteDone(String formation) {
        System.out.println("\n" + "La formation " + formation + " n'existe pas  " + "\n");
    }

    public void entrerIdFormation() {
        System.out.println("\n" + " 1 -> Gérer une session " + "\n");
        System.out.println("\n" + " 2 -> Retour " + "\n");
    }

    /**
     * ***** Formateur ******
     */
    public void menusGererFormateur() {
        System.out.println("\n" + "BIENVENUE DANS LA GESTION DES FORMATEURS : " + "\n");
        System.out.println("\n" + "Faites un choix svp: " + "\n");
        System.out.println("\n" + "1 -> Créer un formateur " + "\n");
        System.out.println("\n" + "2 -> Effacer un formateur " + "\n");
        System.out.println("\n" + "3 -> Update un formateur " + "\n");
        System.out.println("\n" + "4 -> Afficher un formateur " + "\n");
        System.out.println("\n" + "5 -> Ajouter une formation a un formateur " + "\n");
        System.out.println("\n" + "6 -> Supprimer une formation a un formateur" + "\n");
        System.out.println("\n" + "7 -> Retour" + "\n");
    }

    public void ajouterUnFormateur() {
        System.out.println("\n" + "Vous pouvez ajouter un formateur : " + "\n");
    }

    public void entrerNom() {
        System.out.println("\n" + "Entrer le nom : " + "\n");
    }

    public void entrerPrenom() {
        System.out.println("\n" + "Entrer le prénom : " + "\n");
    }

    public void enterTelephone() {
        System.out.println("\n" + "Enter le numéros de téléphone : " + "\n");
    }

    public void enterEmail() {
        System.out.println("\n" + "Entrer votre email : " + "\n");
    }

    public void enterLogin() {
        System.out.println("\n" + "Entrer votre login : " + "\n");
    }

    public void enterPassword() {
        System.out.println("\n" + "Entrer votre password : " + "\n");
    }

    public void entrerAdresse() {
        System.out.println("\n" + "Enter l'adresse du formateur : " + "\n");
    }

    public void afficherLesFormateurs() {
        System.out.println("\n" + "Liste des formateurs : " + "\n");
    }

    public void afficherFormateurs(List<Formateur> listFormateurs) {
        listFormateurs.forEach(form -> {
            System.out.println("\n" + " Id du formateur : " + form.getIdUtilisateur() + "\n" + " Nom : " + form.getNom() + "\n" + " Prénom  : " + form.getPrenom() + "\n" + " Rôle : " + form.getRole().getNomRole() + "\n");
        });
    }

    public void updateFormateur() {
        System.out.println("\n" + "Suivez les étapes pour mettre un jour le fomateur : " + "\n");
    }

    public void listDesFormateurs() {
        System.out.println("\n" + "Voici la liste de vos formations : " + "\n");
    }

    public void afficherListSessionFormateur(List<Session> listSessionFormateur) {
        listSessionFormateur.forEach(session -> {
            System.out.println("\n" + " Id de la session : " + session.getIdSession() + "\n" + "\n" + " id du formateur  :  " + session.getFormateur().getIdUtilisateur() + "\n" + " Date du début de la formation : " + session.getDateDebut() + "\n" + " Date de fin : " + session.getDateFin() + "\n" + " Local : " + session.getLocal().getNomLocal() + "\n");
        });
    }
    //+ " Id de la formation  :  " + session.getFormation().getIdFormation() +
    public void formateurInconnu(String formateur) {
        System.out.println("\n" + " Le formateur " + formateur + " n'existe pas ou il ne donne aucun cours " + "\n");
        System.out.println("\n" + " Vueillez taper de nouveau le nom du formateur ou revenir a la page précédente " + "\n");
    }

    public void effacerFormateur() {
        System.out.println("\n" + "Taper le nom du formateur à effacer : " + "\n");
    }

    public void enterMinPartricipant() {
        System.out.println("\n" + "Entrer le nombre minimum de particpant : " + "\n");
    }

    public void rechercherSessionFormateur() {
        System.out.println("\n" + "Entrer l'ID de la session que vous voulez : " + "\n");
    }

    public void faireUnChoixValide() {
        System.out.println("\n" + " Faites un choix valide " + "\n");
    }

    public void retour() {
        System.out.println("\n" + " Retour  " + "\n");
    }

    public void erreurFormateur() {
        System.out.println("\n" + "Ce formateur n'existe pas : " + "\n");
    }

    /**
     * ***** Session ******
     */
    public void gererSession() {
        System.out.println("\n" + " Faites un choix parmis les propositions suivantes : " + "\n");
        System.out.println("\n" + " 1 -> Créer une session pour la formation choisit " + "\n");
        System.out.println("\n" + " 2 -> Effacer une session de la formation  " + "\n");
        System.out.println("\n" + " 3 -> Editer une session d'une formation  " + "\n");
        System.out.println("\n" + " 4 -> Afficher les sessions de la formation  " + "\n");
        System.out.println("\n" + " 5 -> Afficher une session de la formation  " + "\n");
        System.out.println(" 6 -> Retour  " + "\n");
    }

    public void afficherListSession(List<Session> listSession) {
        listSession.forEach(session -> {
            System.out.println("\n" + " Id de la session : " + session.getIdSession() + "\n" + " Id de la formation  :  "  + "\n" + " id du formateur  :  " + session.getFormateur().getIdUtilisateur() + "\n" + " Date du début de la formation : " + session.getDateDebut() + "\n" + " Date de fin : " + session.getDateFin() + "\n" + " Local : " + session.getLocal().getNomLocal() + "\n");
        });
    }
    //+ session.getFormation().getIdFormation()
    public void afficherListSessionParFormation(List<Session> listSession) {
        listSession.forEach(session -> {
            System.out.println("\n" + " Id de la session : " + session.getIdSession() 
                    + "\n" + " Nom du formateur  :  " + session.getFormateur().getNom()
                    + "\n" + " Date du début de la formation : " + session.getDateDebut() 
                    + "\n" + " Date de fin : " + session.getDateFin() 
                    + "\n" + " Local : " + session.getLocal().getNomLocal() 
                    + "\n" + " Nombre de place disponible : ");
        });
    }

    public void entrerIdSession() {
        System.out.println("\n" + "Entrer l'Id de la session pour avoir le formateur : " + "\n");
    }

    public void afficherSession(Session session) {

        System.out.println(" Id de la session : " + session.getIdSession()
//                + "\n" + " Nom de la formation  :  "
//                + session.getFormation().getIntitule()
                + "\n" + " Nom du formateur  :  "
                + session.getFormateur().getNom()
                + "\n" + " Date du début de la formation : "
                + session.getDateDebut()
                + "\n" + " Date de fin : "
                + session.getDateFin()
                + "\n" + " Local : "
                + session.getLocal().getNomLocal() + "\n");

    }

    public void creerSession() {
        System.out.println("\n" + " Créer une session " + "\n");
    }

    public void entrerDateDebut() {
        System.out.println("\n" + "Enter une date valide sous le format (dd/mm/yyyy) " + "\n");
    }

    ///// possible de la retirer a voir !!!!!!!!!!!!! 
    public void entrerDateFin() {
        System.out.println("\n" + " Enter une date valide sous le format (dd/mm/yyyy) " + "\n");
    }

    public void deleteSession() {
        System.out.println("\n" + " Effacer la session " + "\n");
    }

    public void editerSession() {
        System.out.println("\n" + " Editer la session " + "\n");
    }

    /**
     * ***** Locaux ******
     */
    public void afficherListLocaux(List<Local> listLocaux) {
        for (Local local : listLocaux) {
            System.out.println("\n" + " ID du local : " + local.getIdLocal() + "\n"
                    + " Nom du local : " + local.getNomLocal() + "\n");
        }
    }

    public void erreurLocal() {
        System.out.println("\n" + "Ce local n'existe pas : " + "\n");
    }

    public void erreurSession() {
        System.out.println("\n" + " La session n'existe pas : " + "\n");
    }

    public void displayAllSessionByFormation(List<Formation> listForm) {
        for (Formation form : listForm) {
            afficherListSessionParFormation(form.getListSessionByFormation());
        }
    }

    public void ajouterFormationAuFormateur() {
        System.out.println("\n" + " Ajouter une formation au formateur" + "\n");
    }

    public void listFormationVide() {
        System.out.println("\n" +" Vous donner cours à toutes les formations "+ "\n");
    }

    public void deleteFormationDuFormateur() {
        System.out.println("\n" +" Effacer une formation d'un formateur "+ "\n");
    }

    public void listFormationVidePourFormateur() {
        System.out.println("\n" +" Le formateur n'est affecter à aucune formation "+ "\n");
    }

    public void choixDeLaFormation() {
        System.out.println("\n" +" Choisissez la formation a effacé "+ "\n");
    }
}