package entities;

public class Car {
    private String name;
    private double fuel;
    private double score;
    private boolean turnLost;
    private double weatherMultiplier;
    private double fuelConsumptionRate;
    private double distanceRemaining;

    public Car(String name, double fuel) {
        this.name = name;
        this.fuel = fuel;
        this.score = 0;
        this.turnLost = false;
        this.weatherMultiplier = 1.0;
        this.fuelConsumptionRate = 0.0;
    }
    

    public boolean drive(double miles, RouteType route) {
        double fuelNeeded = miles / fuelEfficiency(); // Calculate required fuel
        if (fuel >= fuelNeeded && miles <= distanceRemaining) {
            fuel -= fuelNeeded; // Deduct fuel
            distanceRemaining -= miles; // Deduct distance
            return true; // Drive successful
        }
        return false; // Not enough fuel or invalid distance
    }

    public double fuelNeededFor(double miles) {
        return miles / fuelEfficiency();
    }

    public String getName() {
        return name;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isTurnLost() {
        return turnLost;
    }

    public void setTurnLost(boolean turnLost) {
        this.turnLost = turnLost;
    }

    public double getWeatherMultiplier() {
        return weatherMultiplier;
    }

    public void setWeatherMultiplier(double weatherMultiplier) {
        this.weatherMultiplier = weatherMultiplier;
    }

    public double getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public void setFuelConsumptionRate(double fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }

    public double getDistanceRemaining() {
        return distanceRemaining;
    }

    public void setDistanceRemaining(double distanceRemaining) {
        this.distanceRemaining = distanceRemaining;
    }

    private double fuelEfficiency() {
        return fuelConsumptionRate;
    }
}
