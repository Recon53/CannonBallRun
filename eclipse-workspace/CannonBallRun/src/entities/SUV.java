package entities;

public class SUV extends Car {
    public SUV(String name, double distanceRemaining, double fuel) {
        super(name, fuel);
    }

    @Override
    public double getFuelConsumptionRate() {
        return 45.0; // Example: SUV fuel efficiency is 20 MPG
    }
}