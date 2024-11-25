package entities;

public class Weather {
    private String condition;
    private double fuelMultiplier;

    public Weather(String condition, double fuelMultiplier) {
        this.condition = condition;
        this.fuelMultiplier = fuelMultiplier;
    }

    public double getFuelMultiplier() {
        return fuelMultiplier;
    }
}