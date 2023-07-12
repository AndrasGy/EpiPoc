package hu.marktsoft.epipoc.repository;

import hu.marktsoft.epipoc.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    List<Travel> findByTravelDate(Date travelDate);
}
