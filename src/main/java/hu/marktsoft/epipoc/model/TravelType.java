package hu.marktsoft.epipoc.model;

import hu.marktsoft.epipoc.service.strategy.BicycleStrategy;
import hu.marktsoft.epipoc.service.strategy.CarStrategy;
import hu.marktsoft.epipoc.service.strategy.MassStrategy;
import hu.marktsoft.epipoc.service.strategy.VehicleStrategy;

public enum TravelType {
    Car,
    Bicycle,
    Mass;

    public VehicleStrategy getStrategy() {
        switch (this) {
            case Car:
                return new CarStrategy();
            case Bicycle:
                return new BicycleStrategy();
            case Mass:
                return new MassStrategy();
            default:
                return null;
        }
    }
}
