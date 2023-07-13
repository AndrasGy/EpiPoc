package hu.marktsoft.epipoc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TravelEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TravelType travelType;

    private Integer numberOfPassengers;
    private Integer averageSpeed;
    private Integer distance;

    private LocalDate travelDate;

    @Min(0)
    @Max(100)
    private Integer comfortFactor;

    @Min(0)
    @Max(100)
    private Integer ecologyFactor;

    @Min(0)
    @Max(100)
    private Integer healthFactor;

    @Version
    private Integer version;

    @Override
    public int hashCode() {
        return id.intValue() * 12345;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return ((TravelEntity) obj).getId().equals(this.getId());
    }
}
