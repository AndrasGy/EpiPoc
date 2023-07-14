package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.controller.UpdateTravelRequest;
import hu.marktsoft.epipoc.dto.AddTravelRequest;
import hu.marktsoft.epipoc.dto.TravelDTO;
import hu.marktsoft.epipoc.model.TravelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TravelMapper {

    TravelDTO travelModelToDTO(TravelEntity travel);

    TravelEntity travelDTOToModel(TravelDTO travelDTO);

    TravelEntity entityFromAddRequest(AddTravelRequest request);

    TravelEntity entityFromUpdateRequest(UpdateTravelRequest request);

    // TODO: ignore null properties
    void update(TravelEntity source, @MappingTarget TravelEntity target);
}
