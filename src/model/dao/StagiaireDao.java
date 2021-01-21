package model.dao;

import java.util.List;
import model.Inscription;
import model.Stagiaire;

public interface StagiaireDao {

    public void addStagiaire(Stagiaire stagiaire);

    public void updateStagiaire(Stagiaire stagiaire);

    public List<Inscription> getListDesInscriptions(Stagiaire stagiaire);

    public Inscription getInscritpionById(int choixInscription);

    public Boolean annulerInscription(Inscription inscription);

    public List<Inscription> getListInscriptionNonPayer(Stagiaire aThis);

    public Boolean signalerPaiement(Inscription inscription);

}
    
