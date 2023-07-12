package hu.marktsoft.epipoc.service.strategy;

public class MassStrategy implements VehicleStrategy {

    public int calculateComfortFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        double passengerDivider = numbersOfPassengers > 25 ? 0.4 : 0.7;

        return (int) Math.ceil(100 * passengerDivider);
    }

    public int calculateEcologyFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        double avarageSpeedDivider = avarageSpeed > 30 ? 0.9 : 0.7;
        double passengerDivider = numbersOfPassengers>25 ? 1 : numbersOfPassengers / 25;

        return (int) Math.ceil(100 * avarageSpeedDivider * passengerDivider);
    }

    public int calculateHealthFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        return distance > 10 ? 80 : 65;
    }
}
