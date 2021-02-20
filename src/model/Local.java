package model;

import model.dao.AbstractDaoFactory;
import model.dao.CentreDao;

public class Local {

    private Integer idLocal;
    private String nomLocal;

    public Local() {
    }

    public Local(Integer idLocal, String nomLocal) {
        this.idLocal = idLocal;
        this.nomLocal = nomLocal;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public Boolean ajoutLocal(Local local) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.ajoutLocal(local);
    }

}
