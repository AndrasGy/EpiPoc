package hu.marktsoft.epipoc.repository;

import hu.marktsoft.epipoc.model.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {

    List<TravelEntity> findByTravelDate(Date travelDate);
}
