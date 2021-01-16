package model.dao;

import java.util.List;
import model.Session;

public interface SessionDao {
    
    /////////// *********** A déplacé ***********//////////:::
    public List<Session> getListSessionByNameFormation(String formationName);

    public List<Session> getListSessionByNameFormateur(String nomFormateur);
    
    public Session getSessionByIdSession(Integer IdSession);
    
    public List<Session> getAllSession();

    public void addSession(Session session,int idFormation);

    public void deleteSessionById(Integer idSession);

    public List<Session> getListSessionByIdFormation(Integer idFormation);

}
    //public List<Session> getListFormationAvecNom(String nameSession);
