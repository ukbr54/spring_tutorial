package formBased.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "APP_USER")
@NoArgsConstructor
public class User {

    @Id @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_USER_ID",referencedColumnName = "ID")
    private List<UserRole> roles;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
