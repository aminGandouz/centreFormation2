
package controler;

import java.util.Scanner;
import model.Model;

public interface ControlerUtils {

    public Scanner s = new Scanner(System.in);
    public Model model = new Model();
    public ControlerAdmin ctrlAdmin = new ControlerAdmin();
    public ControlerStagiaire ctrlStagiaire = new ControlerStagiaire();
    public ControlerFormation ctrlFormation = new ControlerFormation();
    public ControlerLogin ctrlLogin = new ControlerLogin();
    public ControlerSession ctrlSession = new ControlerSession();
    public Controler controler = new Controler();
    public ControlerFormateur ctrlFormateur = new ControlerFormateur();

    default int choixMenus(int min, int max) {// gerer si on met autre chose qu'un int
        int choixDuMenus = s.nextInt();
        while (choixDuMenus < min || choixDuMenus > max) {
            this.erreur();
            choixDuMenus = s.nextInt();
        }
        return choixDuMenus;
    }

    public void erreur();

    default int choixMenus2(int min, int max) {// gerer si on met des espaces 
        int choixDuMenus = 0;       
        do {
            if (s.hasNextInt()) {
                choixDuMenus = s.nextInt();
            }
            
            while (choixDuMenus < min || choixDuMenus > max) {
                this.erreur();
                s.nextLine();
                if (s.hasNextInt()) {
                    choixDuMenus = s.nextInt();
                }
            }           
            return choixDuMenus;           
        } while (!s.hasNextInt());

    }
}

