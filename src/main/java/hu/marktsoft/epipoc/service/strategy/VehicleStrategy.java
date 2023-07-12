package hu.marktsoft.epipoc.service.strategy;

public interface VehicleStrategy {

    public int calculateComfortFactor(int numbersOfPassengers, int avarageSpeed, int distance); // 1 - 100

    public int calculateEcologyFactor(int numbersOfPassengers, int avarageSpeed, int distance); // 1 - 100

    public int calculateHealthFactor(int numbersOfPassengers, int avarageSpeed, int distance); // 1 - 100
}
