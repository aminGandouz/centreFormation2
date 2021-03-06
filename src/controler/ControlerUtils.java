
package controler;

import java.util.Scanner;
import model.Local;
import model.Model;
import vue.VueAdmin;

public interface ControlerUtils {

    public Scanner s = new Scanner(System.in);
    public Model model = new Model();
    public Local local = new Local();
    public ControlerAdmin ctrlAdmin = new ControlerAdmin();
    public ControlerStagiaire ctrlStagiaire = new ControlerStagiaire();
    public ControlerFormation ctrlFormation = new ControlerFormation();
    public ControlerLogin ctrlLogin = new ControlerLogin();
    public ControlerSession ctrlSession = new ControlerSession();
    public Controler controler = new Controler();
    public ControlerFormateur ctrlFormateur = new ControlerFormateur();
    public VueAdmin vueAdmin = new VueAdmin();

    default int choixMenus(int min, int max) {
        int choixDuMenus = s.nextInt();
        while (choixDuMenus < min || choixDuMenus > max) {
            this.erreur();
            choixDuMenus = s.nextInt();
        }
        return choixDuMenus;
    }
    default void checkInt() {
        while (!s.hasNextInt()) {
            vueAdmin.erreurNumberString();
            s.nextLine();
        }
    }

    public void erreur();

    default int choixMenus2(int min, int max) {
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

