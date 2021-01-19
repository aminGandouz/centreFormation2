package model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.dao.AbstractDaoFactory;
import model.dao.SessionDao;

public class Session {

    private Integer idSession;
    private Formateur formateur;
    private Date dateDebut;
    private Date dateFin;
    private Local local;
    //private Float prix;
    private List<Inscription> listInscription;

    public Session() {
    }

    public Session(Integer idSession, Formateur formateur, Date dateDebut, Date dateFin, Local local) {
        this.idSession = idSession;
        this.formateur = formateur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.local = local;
    }

    public Session(Formateur formateur, Date dateDebut, Date dateFin, Local local) {
        this.formateur = formateur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.local = local;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setDateDebutFin(Date debut, int duree) {
        this.setDateDebut(debut);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(debut);
        for (int i = 1; i < duree;) {
            calendar.add(Calendar.DATE, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                i++;
            }
        }
        this.setDateFin(calendar.getTime());
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<Inscription> getListInscription() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        listInscription = sessionDao.getListInscription(this);
        return listInscription;
    }

    public void setListInscription(List<Inscription> listInscription) {
        this.listInscription = listInscription;
    }

    public static void addSession(Session session, int idFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        sessionDao.addSession(session, idFormation);
    }

    public Session getSessionByIdSession(Integer IdSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        return sessionDao.getSessionByIdSession(IdSession);
    }

    public void deleteSessionById(Integer idSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        sessionDao.deleteSessionById(idSession);
    }

}
