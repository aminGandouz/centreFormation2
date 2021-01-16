
package controler;

import static controler.ControlerUtils.model;
import java.util.List;
import model.Formateur;
import model.Session;
import vue.VueFormateur;



public class ControlerFormateur implements ControlerUtils{
    
     /**
     * ***** Champs Attribut     ******
     */
    private VueFormateur vueFormateur = new VueFormateur();
    private List<Session> listSession;
    
     /**
     * ***** All methods     ******
     */
    public void menuFormateur(Formateur formateur) {
        vueFormateur.menuFormateur();
        int choix = choixMenus2(1, 2);
        
        switch(choix){
            case 1:
                listSession = model.getCentre().getListSessionAvecNom(formateur);
                vueFormateur.afficheListSessionNomFormateur(listSession);
                menuFormateur(formateur);
                break;
            case 2: 
                System.exit(0);
                break;              
        }      
    }

     /**
     * ***** Error methods     ******
     */
    @Override
    public void erreur() {
         vueFormateur.erreur();
    }
    
}
