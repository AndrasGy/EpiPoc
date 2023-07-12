package hu.marktsoft.epipoc.service.strategy;

public class BicycleStrategy implements VehicleStrategy {

    public int calculateComfortFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        double avarageSpeedDivider = avarageSpeed > 30 ? 0.4 : 0.7;
        double passengerDivider = numbersOfPassengers > 1 ? 0.2 : 1.0;
        double distanceDivider = distance > 10 ? 0.6 : 1.0;

        return (int) Math.ceil(100 * avarageSpeedDivider * passengerDivider * distanceDivider);
    }

    public int calculateEcologyFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        return 100;
    }

    public int calculateHealthFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        return 100;
    }
}
