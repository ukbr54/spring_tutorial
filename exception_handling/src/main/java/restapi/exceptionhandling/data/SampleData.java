package restapi.exceptionhandling.data;


import restapi.exceptionhandling.bird.BirdMotherObject;
import restapi.exceptionhandling.bird.BirdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SampleData {

    private BirdRepository birdRepository;

    void createSampleData(){
        birdRepository.save(BirdMotherObject.createCanary());
    }
}
