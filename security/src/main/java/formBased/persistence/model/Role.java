package formBased.persistence.model;

public enum Role {

    ADMIN,USER;

    public String authority(){
        return "ROLE_" + this.name();
    }
}
