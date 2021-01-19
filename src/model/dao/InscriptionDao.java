
package model.dao;

import model.Session;
import model.Stagiaire;


public interface InscriptionDao {

    public Boolean ajoutStagiaire(Stagiaire stagiaire, Session session);
    
}
