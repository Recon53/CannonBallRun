package entities;

public class Route {
    private String name;
    private double distance;
    private double riskFactor;

    public Route(String name, double distance, double riskFactor) {
        this.name = name;
        this.distance = distance;
        this.riskFactor = riskFactor;
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}