package hu.marktsoft.epipoc.dto;

import hu.marktsoft.epipoc.model.TravelType;
import lombok.Data;

import java.util.Date;

@Data
public class FactorDTO {

    double comfortFactor;
    double ecologyFactor;
    double healthFactor;

}
