package hu.marktsoft.epipoc;

import hu.marktsoft.epipoc.config.Constants;
import hu.marktsoft.epipoc.dto.FactorDTO;
import hu.marktsoft.epipoc.model.TravelEntity;
import hu.marktsoft.epipoc.model.TravelType;
import hu.marktsoft.epipoc.repository.TravelRepository;
import hu.marktsoft.epipoc.service.TravelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TravelServiceTest {

    @Mock
    TravelRepository travelRepository;
    
    @InjectMocks
    TravelService travelService;

    TravelEntity carTravel;
    TravelEntity bicycleTravel;
    TravelEntity massTravel;

    static final LocalDate GREEN_DAY = LocalDate.now();
    static final LocalDate GREY_DAY = LocalDate.now().minusDays(1);

    @BeforeEach
    public void init() {

        carTravel = new TravelEntity();

        carTravel.setTravelType(TravelType.CAR);
        carTravel.setNumberOfPassengers(1);
        carTravel.setAverageSpeed(25);
        carTravel.setDistance(8);
        carTravel.setTravelDate(GREY_DAY);

        travelService.calculateFactors(carTravel);

        bicycleTravel = new TravelEntity();

        bicycleTravel.setTravelType(TravelType.BICYCLE);
        bicycleTravel.setNumberOfPassengers(1);
        bicycleTravel.setAverageSpeed(19);
        bicycleTravel.setDistance(11);
        bicycleTravel.setTravelDate(GREEN_DAY);

        travelService.calculateFactors(bicycleTravel);

        massTravel = new TravelEntity();

        massTravel.setTravelType(TravelType.MASS);
        massTravel.setNumberOfPassengers(32);
        massTravel.setAverageSpeed(35);
        massTravel.setDistance(10);
        massTravel.setTravelDate(GREEN_DAY);

        travelService.calculateFactors(massTravel);

    }

    @Test
    void calculateCarTravelFactors() {
        assertThat(carTravel.getComfortFactor()).isEqualTo(100);
        assertThat(carTravel.getEcologyFactor()).isEqualTo(10);
        assertThat(carTravel.getHealthFactor()).isEqualTo(40);
    }

    @Test
    void calculateBicycleTravelFactors() {
        assertThat(bicycleTravel.getComfortFactor()).isEqualTo(42);
        assertThat(bicycleTravel.getEcologyFactor()).isEqualTo(100);
        assertThat(bicycleTravel.getHealthFactor()).isEqualTo(100);
    }

    @Test
    void calculateMassTravelFactors() {
        assertThat(massTravel.getComfortFactor()).isEqualTo(40);
        assertThat(massTravel.getEcologyFactor()).isEqualTo(90);
        assertThat(massTravel.getHealthFactor()).isEqualTo(65);
    }

    @Test
    void averageFactorTestWithDate() {
        when(travelRepository.findByTravelDate(GREEN_DAY)).thenReturn(Arrays.asList(bicycleTravel, massTravel));

        FactorDTO factors = travelService.getAverageFactors(GREEN_DAY);

        assertThat(factors.getComfortFactor()).isEqualTo(41);
        assertThat(factors.getEcologyFactor()).isEqualTo(95);
        assertThat(factors.getHealthFactor()).isEqualTo(82.5);
    }

    @Test
    void averageFactorTestWithoutDate() {
        when(travelRepository.findAll()).thenReturn(Arrays.asList(bicycleTravel, massTravel, carTravel));

        FactorDTO factors = travelService.getAverageFactors(null);

        assertThat(Math.ceil(factors.getComfortFactor())).isEqualTo(61);
        assertThat(Math.ceil(factors.getEcologyFactor())).isEqualTo(67);
        assertThat(Math.ceil(factors.getHealthFactor())).isEqualTo(69);
    }

    @Test
    void evaluateTestWithDate() {
        when(travelRepository.findByTravelDate(GREY_DAY)).thenReturn(Arrays.asList(carTravel));

        String evaluation = travelService.getEvaluation(GREY_DAY);

        assertThat(evaluation.contains(Constants.GREAT_COMFORT)).isTrue();
        assertThat(evaluation.contains(Constants.WEAK_ECOLOGY)).isTrue();
        assertThat(evaluation.contains(Constants.AVERAGE_HEALTH)).isTrue();
    }

    @Test
    void evaluateTestWithoutDate() {
        when(travelRepository.findAll()).thenReturn(Arrays.asList(carTravel, bicycleTravel, massTravel));

        String evaluation = travelService.getEvaluation(null);

        assertThat(evaluation.contains(Constants.AVERAGE_COMFORT)).isTrue();
        assertThat(evaluation.contains(Constants.AVERAGE_ECOLOGY)).isTrue();
        assertThat(evaluation.contains(Constants.AVERAGE_HEALTH)).isTrue();
    }
}
