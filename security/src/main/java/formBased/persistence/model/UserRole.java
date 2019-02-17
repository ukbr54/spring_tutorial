package formBased.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "USER_ROLE")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Embeddable
    public static class Id implements Serializable{

        @Column(name = "APP_USER_ID")
        protected Long userId;

        @Enumerated(EnumType.STRING)
        @Column(name = "ROLE")
        protected Role role;

        public Id() {}

        public Id(Long userId,Role role){
            this.userId = userId;
            this.role = role;
        }
    }

    @EmbeddedId
    Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE",insertable = false,updatable = false)
    protected Role role;

    public Role getRole(){
        return role;
    }
}
