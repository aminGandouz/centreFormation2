package model.dao;

import java.util.List;
import model.Formateur;
import model.Formation;
import model.Session;

public interface FormationDao {

    public void ajoutFormation(Formation f);

    public Boolean effacerFormation(Formation formation);

    public void updateFormation(Formation formation);

    public List<Session> getListSessionAvecNom(Formateur formateur);

    public List<Formation> getListFormationAvecNom(String nomFormation);

    public Formation getFormationAvecNom(String nomFormation);

    public Formation getFormationById(Integer idFormation);

    public Formation getFormationByName(String nom);
}
