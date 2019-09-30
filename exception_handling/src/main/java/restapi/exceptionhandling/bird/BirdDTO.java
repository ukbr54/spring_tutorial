package restapi.exceptionhandling.bird;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Ujjwal Gupta on Sep,2019
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BirdDTO {

    private Long id;
    private String name;
    private String specie;
    private Double weight;
    private Integer length;
}
