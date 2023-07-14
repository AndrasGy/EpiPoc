package hu.marktsoft.epipoc.controller;

import hu.marktsoft.epipoc.dto.AddTravelRequest;
import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.dto.TravelDTO;
import hu.marktsoft.epipoc.model.TravelEntity;
import hu.marktsoft.epipoc.service.TravelMapper;
import hu.marktsoft.epipoc.service.TravelService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;
    private final TravelMapper travelMapper;

    @GetMapping
    public List<TravelDTO> findAll() {
        return travelService.findAll().stream().map(t -> travelMapper.travelModelToDTO(t)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TravelDTO findById(@PathParam("id") Long id) {
        Optional<TravelEntity> travelEntity = travelService.findAll().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if (travelEntity.isEmpty()) {
            throw new RuntimeException("Resource not found!");
        }

        return travelMapper.travelModelToDTO(travelEntity.get());
    }

    @GetMapping("/evaluation")
    public String getEvaluation(@RequestParam(value = "date", required = false) LocalDate date) {
        return travelService.getEvaluation(date);
    }

    @GetMapping("/average")
    public FactorDTO getAverageFactors(@RequestParam(value = "date", required = false) LocalDate date) {
        return travelService.getAverageFactors(date);
    }

    @PostMapping
    public void addTravel(@Valid @RequestBody AddTravelRequest request) {
        travelService.addTravel(travelMapper.entityFromAddRequest(request));
    }

    @PutMapping("/{id}")
    public void updateTravel(@PathParam("id") Long id, @Valid @RequestBody UpdateTravelRequest travel) {
        TravelEntity travelEntity = travelMapper.entityFromUpdateRequest(travel);
        travelEntity.setId(id);
        travelService.updateTravel(travelEntity);
    }
}
