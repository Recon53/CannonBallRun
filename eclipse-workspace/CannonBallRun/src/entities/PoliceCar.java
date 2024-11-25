package entities; // Ensure this is the correct package declaration.

public class PoliceCar {
    private double distanceRemaining;
    private double fuel;
    private double speed;

    public PoliceCar(double distanceRemaining, double fuel, double speed) {
        this.distanceRemaining = distanceRemaining;
        this.fuel = fuel;
        this.speed = speed;
    }

    public double move() {
        double milesDriven = Math.min(speed, distanceRemaining);
        double fuelNeeded = milesDriven / 10.0; // Assuming 10 MPG efficiency
        if (fuelNeeded > fuel) {
            milesDriven = fuel * 10.0; // Adjust miles if fuel is insufficient
            fuel = 0;
        } else {
            fuel -= fuelNeeded;
        }
        distanceRemaining = Math.max(0, distanceRemaining - milesDriven);
        return milesDriven;
    }

    public double getDistanceRemaining() {
        return distanceRemaining;
    }

    public double getFuel() {
        return fuel;
    }

    @Override
    public String toString() {
        return "Police - Distance Remaining: " + String.format("%.2f", distanceRemaining) +
                " miles, Fuel: " + String.format("%.2f", fuel) + " gallons.";
    }
}