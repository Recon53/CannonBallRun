package entities;

public class Terrain {
    private String type;
    private double fuelMultiplier;

    public Terrain(String type, double fuelMultiplier) {
        this.type = type;
        this.fuelMultiplier = fuelMultiplier;
    }

    public double getFuelMultiplier() {
        return fuelMultiplier;
    }
}