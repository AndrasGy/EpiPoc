package hu.marktsoft.epipoc.service.strategy;

public class CarStrategy implements VehicleStrategy {

    public int calculateComfortFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        if (numbersOfPassengers > 5) return 10;
        return numbersOfPassengers < 5 ? 100 : 50;
    }

    public int calculateEcologyFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        //avarage speed down -> ecology down , numbersofPassengers down -> ecology down
        double avarageSpeedDivider = avarageSpeed > 30 ? 0.8 : 0.5;
        double passengerDivider = numbersOfPassengers / 5.0D;

        return (int) Math.ceil(100 * avarageSpeedDivider * passengerDivider);
    }

    public int calculateHealthFactor(int numbersOfPassengers, int avarageSpeed, int distance) {
        return distance > 10 ? 60 : 40;
    }
}
