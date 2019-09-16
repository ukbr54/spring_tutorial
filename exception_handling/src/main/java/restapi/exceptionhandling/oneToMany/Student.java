package restapi.exceptionhandling.oneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ujjwal Gupta on Sep,2019
 */

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "mobile_id")
    private List<Mobile> numbers = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }
}
