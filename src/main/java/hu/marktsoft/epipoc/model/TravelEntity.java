package hu.marktsoft.epipoc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class TravelEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private TravelType travelType;

    private int numberOfPassengers;
    private int averageSpeed;
    private int distance;

    private Date travelDate;

    @Min(0)
    @Max(100)
    private int comfortFactor;

    @Min(0)
    @Max(100)
    private int ecologyFactor;

    @Min(0)
    @Max(100)
    private int healthFactor;

    @Version
    private Integer version;

}
