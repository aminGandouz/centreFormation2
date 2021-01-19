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
        s.nextLine();
        String dateDeb = null;
            vueAdmin.entrerDateDebut();
            dateDeb = s.nextLine();
        Date d = null;
        try {
            d = new SimpleDateFormat("dd-MM-yyyy").parse(dateDeb);
            //session.setDateDebut(sdf.parse(s.nextLine()));            
        } catch (ParseException ex) {
            //Logger.getLogger(ControlerSession.class.getName()).log(Level.SEVERE, null, ex);
            vueAdmin.problemeFormatDate();
            addSession(form);
        }
        session.setDateDebut(d);
        session.setDateDebutFin(d, form.getDuree());
        List<Formateur> listFormateurDispo = model.getCentre().getFormateurByFormation(form,session);
        vueFormateur.afficheListFormateur(listFormateurDispo);
        if (listFormateurDispo.isEmpty()) {
            vueAdmin.aucunFormateur();
            ctrlFormation.gererSessionFormation(form);
        }
        vueAdmin.faireUnChoixValide();
        int choixFormateur = s.nextInt();
        Formateur f = new Formateur();
        f = (Formateur) model.getCentre().getUserById(choixFormateur);
        if (f != null) {
            session.setFormateur(f);
        } else {
            vueAdmin.erreurFormateur();
            ctrlFormation.gererSessionFormation(form);
        }
        s.nextLine();
        List<Local> listLocauxDispo = model.getCentre().getLocauxDispo(session);
        vueAdmin.afficherListLocaux(listLocauxDispo);
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
        Session.addSession(session, form.getIdFormation());
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
/* and session.DateDebut not BETWEEN "01-01-2021" and " 21-03-2021" and session.DateFin not BETWEEN  "01-01-2021" and " 21-03-2021"
 group by utilisateur.IdUtilisateur */