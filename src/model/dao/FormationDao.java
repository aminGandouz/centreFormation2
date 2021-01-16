package model.dao;

import java.util.List;
import model.Formateur;
import model.Formation;
import model.Session;

public interface FormationDao {

    public void ajoutFormation(Formation f);

    public void effacerFormation(String formation);

    public void updateFormation(int idFormation, String nomFormation, float prix, int duree, int maxParticipant, int nbreParticipantMin);

   ////////// ********** A déplacé ***********//////////:
    public List<Session> getListSessionAvecNom(Formateur formateur);

    public List<Formation> getListFormationAvecNom(String nomFormation);

    public Formation getFormationAvecNom(String nomFormation);

    public Formation getFormationById(Integer idFormation);
}
