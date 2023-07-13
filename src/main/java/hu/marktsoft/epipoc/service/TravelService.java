package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.model.TravelEntity;

import java.time.LocalDate;
import java.util.List;

public interface TravelService {

    void addTravel(TravelEntity travel);

    void calculateFactors(TravelEntity travel);

    List<TravelEntity> findAll();

    FactorDTO getAverageFactors(LocalDate date);

    String getEvaluation(LocalDate date);

    public void updateTravel(TravelEntity travel);
}
