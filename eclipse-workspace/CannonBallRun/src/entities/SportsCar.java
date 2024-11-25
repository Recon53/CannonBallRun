package entities;

public class SportsCar extends Car {
    public SportsCar(String name, double distanceRemaining, double fuel) {
        super(name, fuel); // Call the Car constructor
    }

    @Override
    public double getFuelConsumptionRate() {
        return 25.0; // Example: SportsCar fuel efficiency is 25 MPG
    }
}