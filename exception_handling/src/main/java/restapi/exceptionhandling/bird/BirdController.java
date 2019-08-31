package restapi.exceptionhandling.bird;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bird")
public class BirdController {

    @Autowired
    private BirdService birdService;

    @PostMapping
    public Bird createBird(@RequestBody Bird bird){
        log.info("Creating the Bird: "+bird.getScientificName());
        return birdService.createBird(bird);
    }
}
