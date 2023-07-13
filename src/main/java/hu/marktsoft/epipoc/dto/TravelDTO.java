package hu.marktsoft.epipoc.dto;

import hu.marktsoft.epipoc.model.TravelType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelDTO {

    private Long id;

    @NotNull(message = "TravelType is mandatory")
    private TravelType travelType;

    @Positive
    private int numberOfPassengers;
    @Positive
    private int averageSpeed;
    @Positive
    private int distance;

    private LocalDate travelDate;

}
