package model.dao;

import java.util.List;
import model.Inscription;
import model.Stagiaire;

public interface StagiaireDao {

    public void addStagiaire(Stagiaire stagiaire);

    public void updateStagiaire(Stagiaire stagiaire);

    public List<Inscription> getListDesInscriptions(Stagiaire stagiaire);

}
    
