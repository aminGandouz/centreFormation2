package model;

import java.util.Comparator;
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

    public Boolean ajoutStatus(Status newStatus) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.ajoutStatus(newStatus);
    }

    public static Comparator<Status> ComparatorId = new Comparator<Status>() {

        @Override
        public int compare(Status e1, Status e2) {
            return (int)e1.getIdStatus().compareTo(e2.getIdStatus());
        }
    };

    @Override
    public String toString() {
        return "Status{" + "idStatus=" + idStatus + ", nomStatus=" + nomStatus + '}';
    }

}
