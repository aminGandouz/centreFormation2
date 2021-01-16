
package model;

public class Role {
    private Integer idRole;
    private String nomRole;

    public Role() {
    }

    public Role(Integer idRole, String nomRole) {
        this.idRole = idRole;
        this.nomRole = nomRole;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
    
}
