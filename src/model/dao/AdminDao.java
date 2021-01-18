package model.dao;

import java.util.List;
import model.Formateur;

public interface AdminDao {

    public void ajoutFormateur(Formateur formateur);

    public void effacerFormateur(Formateur formateur);

    public void updateFormateur(Formateur formateur);
    
    public void deleteFormateurByName(String formateur);
    
    /////********* A déplacé *******///////// 

    public List<Formateur> getListFormateurs();

    public Formateur getFormateurByName(String nomFormateur);

    

}
