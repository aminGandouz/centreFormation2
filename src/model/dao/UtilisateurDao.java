package model.dao;

import java.util.List;
import model.Inscription;
import model.Utilisateur;

public interface UtilisateurDao {

    public Utilisateur getAuthentification(String login, String password);

    public Utilisateur authentificationLogin(String loginString);

    //////////: ************ A déplacé ************////////////::
    public List<Inscription> getListFormationAvecNom(String nomFormation);

    public Utilisateur getUserById(int idUser);

}
