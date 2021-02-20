package model.dao;

import java.util.List;
import model.Inscription;
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

    public List<Session> getListSessionDispoByIdFormation(Integer choixDeLaFormation);

    public Session getListSessionByIdSession(Integer idSess);

    public List<Inscription> getListInscription(Session aThis);

    public List<String> getListInscriptionBySession(Session aThis);

    public void editerSession(Session aThis);

}

