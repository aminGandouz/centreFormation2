package model.dao;

import java.util.List;
import model.Formateur;
import model.Formation;
import model.Session;

public interface AdminDao {

    public void ajoutFormateur(Formateur formateur);

    public void effacerFormateur(Formateur formateur);

    public void updateFormateur(Formateur formateur);

    public void deleteFormateurByName(String formateur);

    public List<Formateur> getFormateurByFormation(Formation form,Session session);

    /////********* A déplacé *******///////// 
    public List<Formateur> getListFormateurs();

    public Formateur getFormateurByName(String nomFormateur);

}
