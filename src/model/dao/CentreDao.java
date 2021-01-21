package model.dao;

import java.util.List;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Session;
import model.Status;

public interface CentreDao {

    public List<Formation> getListFormation();

    public List<Status> getAllStatus();

    public List<Local> getAllLocaux();

    public Local getLocalById(int idLocal);

    public List<Formation> getListFormationByIdFormateur(Integer idUtilisateur);

    public void ajoutFormationAuFormateur(Integer idUtilisateur, Integer idFormation);

    public List<Formation> getListDuFormateur(Integer idUtilisateur);

    public Boolean deleteFormationDuFormateur(int idFormateur, int idFormation);

    public Boolean isFormateurExist(String formateur);

    public List<Formation> getListFormationsByNameFormation(String nomFormation);

    public List<Local> getLocauxDispo(Session session);

    public void cleanDB();

    public Boolean ajoutStatus(Status newStatus);

    public Boolean ajoutLocal(Local local);

    public List<Inscription> getListDesInscriptions();

    public Inscription getInscritpionById(int choix);

}
    //public List<Utilisateur> getListformateur();
