package restapi.exceptionhandling.bird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.exceptionhandling.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BirdService {

    @Autowired
    private BirdRepository birdRepository;

    public Bird createBird(Bird bird){
        return birdRepository.save(bird);
    }

    public Bird getBird(Long birdId) throws EntityNotFoundException{
        final Optional<Bird> bird = birdRepository.findById(birdId);
        if(!bird.isPresent()){
            throw new EntityNotFoundException(Bird.class,"id",birdId.toString());
        }
        return bird.get();
    }

    public List<Bird> getBirdCollection(BirdCollection birdCollection) throws EntityNotFoundException {
        List<Bird> birds = new ArrayList<>();

        for (Long birdId : birdCollection.getBirdsIds()) {
            final Optional<Bird> bird = birdRepository.findById(birdId);
            if (!bird.isPresent()) {
                throw new EntityNotFoundException(Bird.class, "id", birdId.toString());
            }
            birds.add(bird.get());
        }
        return birds;
    }
}
