package restapi.exceptionhandling.oneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Ujjwal Gupta on Sep,2019
 */

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "mobile")
@Table(name = "mobile")
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    public Mobile(String number) {
        this.number = number;
    }
}
