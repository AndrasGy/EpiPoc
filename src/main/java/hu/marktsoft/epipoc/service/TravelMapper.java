package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.dto.TravelDTO;
import hu.marktsoft.epipoc.model.TravelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TravelMapper {
    TravelDTO travelModelToDTO(TravelEntity travel);

    TravelEntity travelDTOToModel(TravelDTO travelDTO);
}
