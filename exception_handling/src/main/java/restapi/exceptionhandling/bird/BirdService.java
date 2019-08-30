package restapi.exceptionhandling.bird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirdService {

    @Autowired
    private BirdRepository birdRepository;

    public Bird createBird(Bird bird){
        return birdRepository.save(bird);
    }
}
