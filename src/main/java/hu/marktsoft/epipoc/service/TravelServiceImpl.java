package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.config.Constants;
import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.model.TravelEntity;
import hu.marktsoft.epipoc.repository.TravelRepository;
import hu.marktsoft.epipoc.service.strategy.VehicleStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class TravelServiceImpl implements TravelService {

    private final TravelRepository travelRepository;
    private final TravelMapper travelMapper;

    @Override
    public void addTravel(TravelEntity travel) {
        Objects.requireNonNull(travel);

        calculateFactors(travel);

        travelRepository.save(travel);
    }

    @Override
    public void calculateFactors(TravelEntity travelEntity) {
        VehicleStrategy strategy = travelEntity.getTravelType().getStrategy();

        // TODO: NPE veszély paraméterátadásnál!
        travelEntity.setComfortFactor(strategy.calculateComfortFactor(travelEntity.getNumberOfPassengers(), travelEntity.getAverageSpeed(), travelEntity.getDistance()));
        travelEntity.setEcologyFactor(strategy.calculateEcologyFactor(travelEntity.getNumberOfPassengers(), travelEntity.getAverageSpeed(), travelEntity.getDistance()));
        travelEntity.setHealthFactor(strategy.calculateHealthFactor(travelEntity.getNumberOfPassengers(), travelEntity.getAverageSpeed(), travelEntity.getDistance()));
    }

    @Override
    public List<TravelEntity> findAll() {
        return travelRepository.findAll();
    }

    public FactorDTO getAverageFactors(LocalDate date) {
        FactorDTO avarageFactors = new FactorDTO();
        List<TravelEntity> travels = (date == null) ? travelRepository.findAll() : travelRepository.findByTravelDate(date);

        avarageFactors.setComfortFactor(travels.stream().collect(Collectors.averagingInt(TravelEntity::getComfortFactor)));
        avarageFactors.setEcologyFactor(travels.stream().collect(Collectors.averagingInt(TravelEntity::getEcologyFactor)));
        avarageFactors.setHealthFactor(travels.stream().collect(Collectors.averagingInt(TravelEntity::getHealthFactor)));

        return avarageFactors;
    }

    @Override
    public String getEvaluation(LocalDate date) {
        String evaluation = "";
        FactorDTO avarageFactors = getAverageFactors(date);

        if (avarageFactors.getComfortFactor() >= Constants.GREAT_LIMIT) {
            evaluation += Constants.GREAT_COMFORT;
        }
        else if (avarageFactors.getComfortFactor() >= Constants.AVERAGE_LIMIT) {
            evaluation += Constants.AVERAGE_COMFORT;
        }
        else {
            evaluation += Constants.WEAK_COMFORT;
        }

        if (avarageFactors.getEcologyFactor() >= Constants.GREAT_LIMIT) {
            evaluation += Constants.GREAT_ECOLOGY;
        }
        else if (avarageFactors.getEcologyFactor() >= Constants.AVERAGE_LIMIT) {
            evaluation += Constants.AVERAGE_ECOLOGY;
        }
        else {
            evaluation += Constants.WEAK_ECOLOGY;
        }

        if (avarageFactors.getHealthFactor() >= Constants.GREAT_LIMIT) {
            evaluation += Constants.GREAT_HEALTH;
        }
        else if (avarageFactors.getHealthFactor() >= Constants.AVERAGE_LIMIT) {
            evaluation += Constants.AVERAGE_HEALTH;
        }
        else {
            evaluation += Constants.WEAK_HEALTH;
        }

        return evaluation;
    }

    @Transactional
    @Override
    public void updateTravel(TravelEntity travelEntity) {
        TravelEntity oldTravel = travelRepository.getReferenceById(travelEntity.getId());
        travelMapper.update(travelEntity, oldTravel);
        travelRepository.save(oldTravel);
    }
}
