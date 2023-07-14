package hu.marktsoft.epipoc.controller;

import hu.marktsoft.epipoc.model.TravelType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTravelRequest {
    private TravelType travelType;
    private int numberOfPassengers;
    private int averageSpeed;
    private int distance;
    private LocalDate travelDate;
    private Integer version;
}
