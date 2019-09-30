package restapi.exceptionhandling.bird;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Ujjwal Gupta on Sep,2019
 */

@Mapper(componentModel = "Spring")
public interface BirdMapper {

    BirdMapper INSTANCE = Mappers.getMapper(BirdMapper.class);

    @Mappings({
            @Mapping(source = "scientificName",target = "name"),
            @Mapping(source = "mass",target = "weight")
    })
    BirdDTO birdToBirdDTO(Bird bird);

    List<BirdDTO> birdToBirdDTOS(List<Bird> birds);
}
