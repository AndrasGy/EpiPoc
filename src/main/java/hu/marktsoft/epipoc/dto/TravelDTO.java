package hu.marktsoft.epipoc.dto;

import hu.marktsoft.epipoc.model.TravelType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class TravelDTO {

    Long id;

    @NotNull(message = "TravelType is mandatory")
    TravelType travelType;

    @Positive
    int numberOfPassengers;
    @Positive
    int averageSpeed;
    @Positive
    int distance;
    Date travelDate;

}
