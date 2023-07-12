package hu.marktsoft.epipoc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;
import lombok.NonNull;

import java.util.Date;

@Entity
@Data
public class Travel {

    @Id
    @GeneratedValue
    Long id;

    @NotNull
    TravelType travelType;

    int numberOfPassengers;
    int averageSpeed;
    int distance;

    Date travelDate;

    @Min(0)
    @Max(100)
    int comfortFactor;

    @Min(0)
    @Max(100)
    int ecologyFactor;

    @Min(0)
    @Max(100)
    int healthFactor;

    @Version
    private Integer version;

}
