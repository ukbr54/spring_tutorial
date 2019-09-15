package restapi.exceptionhandling.bird;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restapi.exceptionhandling.exception.EntityNotFoundException;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/bird")
public class BirdController {

    @Autowired
    private BirdService birdService;

    @Autowired
    private BirdMapper birdMapper;


    /**
     * {
     *     "status": "NOT_FOUND",
     *     "timestamp": "31-08-2019 09:01:16",
     *     "message": "Bird was not found for parameters {id=2}",
     *     "debugMessage": null,
     *     "subErrors": null
     * }
     * @param birdId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping(value = "/{birdId}")
    public BirdDTO getBird(@PathVariable("birdId") Long birdId) throws EntityNotFoundException {
        Bird bird = birdService.getBird(birdId);
        return birdMapper.birdToBirdDTO(bird);
    }


    /**
     * {
     *     "status": "BAD_REQUEST",
     *     "timestamp": "31-08-2019 07:04:47",
     *     "message": "Malformed JSON request",
     *     "debugMessage": "JSON parse error: Cannot deserialize value of type `java.lang.Double` from String \"aaa\": not a valid Double value; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Double` from String \"aaa\": not a valid Double value\n at [Source: (PushbackInputStream); line: 4, column: 10] (through reference chain: restapi.exceptionhandling.bird.Bird[\"mass\"])",
     *     "subErrors": null
     * }
     * @param bird
     * @return
     */
//    @PostMapping
//    public Bird createBirdWithMalformedJSON(@RequestBody Bird bird){
//        log.info("Creating the Bird: "+bird.getScientificName());
//        return birdService.createBird(bird);
//    }

    /**
     * {
     *     "status": "BAD_REQUEST",
     *     "timestamp": "31-08-2019 09:10:06",
     *     "message": "birdId parameter is missing",
     *     "debugMessage": "Required Long parameter 'birdId' is not present",
     *     "subErrors": null
     * }
     * @param birdId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping(value = "/params")
    public Bird getBirdRequestParam(@RequestParam("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBird(birdId);
    }

    @PostMapping
    public Bird createBird(@RequestBody @Valid Bird bird) {
        return birdService.createBird(bird);
    }
}
