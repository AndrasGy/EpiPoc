package hu.marktsoft.epipoc.dto;

import hu.marktsoft.epipoc.model.TravelType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelDTO {

    private Long id;
    private TravelType travelType;
    private int numberOfPassengers;
    private int averageSpeed;
    private int distance;
    private LocalDate travelDate;
    private Integer version;
}
