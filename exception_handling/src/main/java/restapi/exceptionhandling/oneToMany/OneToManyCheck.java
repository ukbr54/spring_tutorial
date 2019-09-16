package restapi.exceptionhandling.oneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ujjwal Gupta on Sep,2019
 */

@RestController
public class OneToManyCheck {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/test")
    public ResponseEntity testOneToManyInSpring(){

        Student student = new Student("First");

        student.getNumbers().add(new  Mobile("1234567890"));
        student.getNumbers().add(new  Mobile("4567890"));
        student.getNumbers().add(new  Mobile("34567890"));

        studentRepository.save(student);

        return new ResponseEntity(HttpStatus.OK);
    }
}
