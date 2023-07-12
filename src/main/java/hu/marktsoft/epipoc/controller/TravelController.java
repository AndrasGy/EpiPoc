package hu.marktsoft.epipoc.controller;

import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.dto.TravelDTO;
import hu.marktsoft.epipoc.model.Travel;
import hu.marktsoft.epipoc.service.TravelMapper;
import hu.marktsoft.epipoc.service.TravelMapperImpl;
import hu.marktsoft.epipoc.service.TravelService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    TravelService travelService;
    private TravelMapper travelMapper = Mappers.getMapper(TravelMapper.class);

    @GetMapping
    public List<TravelDTO> findAll() {
        return travelService.findAll().stream().map(t -> travelMapper.travelModelToDTO(t)).collect(Collectors.toList());
    }

    @GetMapping("/evaluation")
    public String getEvaluation(@RequestParam(value = "date", required = false) Date date) {
        return travelService.getEvaluation(date);
    }

    @GetMapping("/avarage")
    public FactorDTO getAverageFactors(@RequestParam(value = "date", required = false) Date date) {
        return travelService.getAverageFactors(date);
    }

    @PostMapping
    public void addTravel(@Valid @RequestBody TravelDTO travel){
        travelService.addTravel(travelMapper.travelDTOToModel(travel));
    }

    @PutMapping("/{id}")
    public void updateTravel(@PathParam("id") Long id, @Valid @RequestBody TravelDTO travel) {
        travel.setId(id);
        travelService.updateTravel(travelMapper.travelDTOToModel(travel));
    }
}
