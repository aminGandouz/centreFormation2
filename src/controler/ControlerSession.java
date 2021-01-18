package controler;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Formateur;
import model.Formation;
import model.Local;
import model.Session;
import vue.VueAdmin;
import vue.VueFormateur;

public class ControlerSession implements ControlerUtils {

    private final VueAdmin vueAdmin = new VueAdmin();
    private final VueFormateur vueFormateur = new VueFormateur();
    private Session session = new Session();
    private Formation formation = new Formation();
    private Formateur formateur = new Formateur();
    private Local local = new Local();
  

    @Override
    public void erreur() {
        ///// 
    }
    
    ////// !!!!!!!!!!!!!! problème avec addSession Date Date Date Calendar Calendar Calendar
    public void addSession(Formation form) {
        formation = model.getCentre().getFormationById(form.getIdFormation());
        //session.setFormation(formation);
        vueAdmin.faireUnChoixValide();

        List<Formateur> listFormateur = model.getCentre().getAllFormateur();
        vueFormateur.afficheListFormateur(listFormateur);
        vueAdmin.faireUnChoixValide();
        int choixFormateur = s.nextInt();
        Formateur f = new Formateur();
        f = (Formateur) model.getCentre().getUserById(choixFormateur);
        if (f != null) {
            session.setFormateur(f);
        } else {
            vueAdmin.erreurFormateur();
            addSession(form);
        }
        s.nextLine();

        vueAdmin.entrerDateDebut();
        String dateDeb = s.nextLine();
        Date d = null;
        try {
            d = new SimpleDateFormat("dd-MM-yyyy").parse(dateDeb);
            //session.setDateDebut(sdf.parse(s.nextLine()));            
        } catch (ParseException ex) {
            Logger.getLogger(ControlerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setDateDebut(d);
        session.setDateDebutFin(d, form.getDuree());
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(session.getDateDebut());
//        calendar.add(Calendar.DAY_OF_MONTH, form.getDuree());
//        String dateFin = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
//        try {
//            session.setDateFin(new SimpleDateFormat("dd-MM-yyyy").parse(dateFin));
//
//        } catch (ParseException ex) {
//            Logger.getLogger(ControlerSession.class.getName()).log(Level.SEVERE, null, ex);
//        }

        List<Local> listLocaux = model.getCentre().getAllLocaux();
        vueAdmin.afficherListLocaux(listLocaux);
        vueAdmin.faireUnChoixValide();
        int choixlocal = s.nextInt();
        Local local = new Local();
        local = model.getCentre().getLocalById(choixlocal);
        if (local != null) {
            session.setLocal(local);
        } else {
            vueAdmin.erreurLocal();
            addSession(form);
        }

        Session.addSession(session,form.getIdFormation());

    }

    public void deleteSession(Formation form) {
        List<Session> listSession = model.getForm().getListSessionByNameFormation(form.getIntitule());
        vueAdmin.afficherListSession(listSession);
        vueAdmin.faireUnChoixValide();
        int choixSession = s.nextInt();
        Session sess = new Session();
        sess = (Session) model.getSess().getSessionByIdSession(choixSession);
        if (sess != null) {
            session.deleteSessionById(sess.getIdSession());
        } else {
            vueAdmin.erreurSession();
            deleteSession(form);
        }
    }

}
