package model.dao;

import java.util.List;
import model.Formateur;
import model.Formation;
import model.Local;
import model.Status;

public interface CentreDao {

    public List<Formation> getListFormation();

    public List<Status> getAllStatus();

    public List<Local> getAllLocaux();

    public Local getLocalById(int idLocal);

    public List<Formation> getListFormationByIdFormateur(Integer idUtilisateur);

    public void ajoutFormationAuFormateur(Integer idUtilisateur, Integer idFormation);

    public List<Formation> getListDuFormateur(Integer idUtilisateur);

    public void deleteFormationDuFormateur(int idFormateur, int idFormation);

    public Boolean isFormateurExist(String formateur);

}
    //public List<Utilisateur> getListformateur();