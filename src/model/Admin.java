package model;

public class Admin extends Utilisateur {

    private Model model;

    public Admin() {
    }

    public Admin(Integer idUtilisateur, String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role) {
        super(idUtilisateur, nom, prenom, adresse, telephone, email, login, password, role);
    }

    public Admin(String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role) {
        super(nom, prenom, adresse, telephone, email, login, password, role);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
