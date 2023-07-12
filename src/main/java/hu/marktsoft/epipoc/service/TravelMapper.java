package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.dto.TravelDTO;
import hu.marktsoft.epipoc.model.Travel;
import org.mapstruct.Mapper;

@Mapper
public interface TravelMapper {
    TravelDTO travelModelToDTO(Travel travel);
    Travel travelDTOToModel(TravelDTO travelDTO);
}
