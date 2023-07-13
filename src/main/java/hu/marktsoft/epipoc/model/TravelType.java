package hu.marktsoft.epipoc.model;

import hu.marktsoft.epipoc.service.strategy.BicycleStrategy;
import hu.marktsoft.epipoc.service.strategy.CarStrategy;
import hu.marktsoft.epipoc.service.strategy.MassStrategy;
import hu.marktsoft.epipoc.service.strategy.VehicleStrategy;

public enum TravelType {
    CAR,
    BICYCLE,
    MASS;

    public VehicleStrategy getStrategy() {
        switch (this) {
            case CAR:
                return new CarStrategy();
            case BICYCLE:
                return new BicycleStrategy();
            case MASS:
                return new MassStrategy();
            default:
                return null;
        }
    }
}
