package model;

import model.dao.AbstractDaoFactory;
import model.dao.CentreDao;

public class Status {

    private Integer idStatus;
    private String nomStatus;
    private Double reduction;

    public Status() {
    }

    public Status(String nomStatus, Double reduction) {
        this.nomStatus = nomStatus;
        this.reduction = reduction;
    }

    public Status(Integer idStatus, String nomStatus) {
        this.idStatus = idStatus;
        this.nomStatus = nomStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNomStatus() {
        return nomStatus;
    }

    public void setNomStatus(String nomStatus) {
        this.nomStatus = nomStatus;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    @Override
    public String toString() {
        return "Status{" + "idStatus=" + idStatus + ", nomStatus=" + nomStatus + '}';
    }

    public Boolean ajoutStatus(Status newStatus) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.ajoutStatus(newStatus);
    }

}
