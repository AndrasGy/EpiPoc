package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.config.Constants;
import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.model.Travel;
import hu.marktsoft.epipoc.repository.TravelRepository;
import hu.marktsoft.epipoc.service.strategy.VehicleStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;
    @Autowired
    EntityManager entityManager;
    public void addTravel(Travel travel) {
        calculateFactors(travel);

        travelRepository.save(travel);
    }

    public void calculateFactors( Travel travel){
        VehicleStrategy strategy = travel.getTravelType().getStrategy();

        travel.setComfortFactor(strategy.calculateComfortFactor(travel.getNumberOfPassengers(), travel.getAverageSpeed(), travel.getDistance()));
        travel.setEcologyFactor(strategy.calculateEcologyFactor(travel.getNumberOfPassengers(), travel.getAverageSpeed(), travel.getDistance()));
        travel.setHealthFactor(strategy.calculateHealthFactor(travel.getNumberOfPassengers(), travel.getAverageSpeed(), travel.getDistance()));
    }

    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    public FactorDTO getAverageFactors(Date date) {
        FactorDTO avarageFactors = new FactorDTO();
        List<Travel> travels = (date == null) ? travelRepository.findAll() : travelRepository.findByTravelDate(date);

        avarageFactors.setComfortFactor(travels.stream().collect(Collectors.averagingInt(Travel::getComfortFactor)));
        avarageFactors.setEcologyFactor(travels.stream().collect(Collectors.averagingInt(Travel::getEcologyFactor)));
        avarageFactors.setHealthFactor(travels.stream().collect(Collectors.averagingInt(Travel::getHealthFactor)));

        return avarageFactors;
    }

    public String getEvaluation(Date date) {
        String evaluation = "";
        FactorDTO avarageFactors = getAverageFactors(date);

        if (avarageFactors.getComfortFactor() >= Constants.GREAT_LIMIT) evaluation+= Constants.GREAT_COMFORT;
        else if (avarageFactors.getComfortFactor() >= Constants.AVERAGE_LIMIT) evaluation+= Constants.AVERAGE_COMFORT;
        else evaluation+= Constants.WEAK_COMFORT;

        if (avarageFactors.getEcologyFactor() >= Constants.GREAT_LIMIT) evaluation+= Constants.GREAT_ECOLOGY;
        else if (avarageFactors.getEcologyFactor() >= Constants.AVERAGE_LIMIT) evaluation+= Constants.AVERAGE_ECOLOGY;
        else evaluation+= Constants.WEAK_ECOLOGY;

        if (avarageFactors.getHealthFactor() >= Constants.GREAT_LIMIT) evaluation+= Constants.GREAT_HEALTH;
        else if (avarageFactors.getHealthFactor() >= Constants.AVERAGE_LIMIT) evaluation+= Constants.AVERAGE_HEALTH;
        else evaluation+= Constants.WEAK_HEALTH;

        return evaluation;
    }

    @Transactional
    public void updateTravel(Travel travel) {
        Travel oldTravel = travelRepository.getReferenceById(travel.getId());
        entityManager.lock(oldTravel, LockModeType.OPTIMISTIC);

        oldTravel.setTravelDate(travel.getTravelDate());
        oldTravel.setTravelType(travel.getTravelType());
        oldTravel.setDistance(travel.getDistance());
        oldTravel.setAverageSpeed(travel.getAverageSpeed());
        oldTravel.setNumberOfPassengers(travel.getNumberOfPassengers());


        travelRepository.save(oldTravel);

        entityManager.lock(oldTravel, LockModeType.NONE);
    }
}
