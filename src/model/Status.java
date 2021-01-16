
package model;

public class Status {
    private Integer idStatus;
    private String nomStatus;

    public Status() {
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
    
}
