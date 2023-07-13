package hu.marktsoft.epipoc.service;

import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.model.TravelEntity;

import java.util.Date;
import java.util.List;

public interface TravelService {

    void addTravel(TravelEntity travel);

    void calculateFactors(TravelEntity travel);

    List<TravelEntity> findAll();

    FactorDTO getAverageFactors(Date date);

    String getEvaluation(Date date);

    public void updateTravel(TravelEntity travel);
}
