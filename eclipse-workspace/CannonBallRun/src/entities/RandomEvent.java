package entities;

import java.util.Random;
import java.util.Scanner;

public class RandomEvent {
    private String eventName;
    private String description;
    private double probability; // Probability of the event occurring

    public RandomEvent(String eventName, String description, double probability) {
        this.eventName = eventName;
        this.description = description;
        this.probability = probability;
    }

    // Determines if the event occurs based on its probability
    public boolean occurs() {
        Random random = new Random();
        return random.nextDouble() < probability;
    }

    // Applies the effect of the event to the player
    public void applyEffect(Car player) {
        System.out.println("Event: " + eventName + " - " + description);
        switch (eventName) {
            case "Police Roadblock":
                handlePoliceRoadblock(player);
                break;
            case "Fuel Leak":
                handleFuelLeak(player);
                break;
            case "Speed Trap":
                handleSpeedTrap(player);
                break;
            case "Weather Change":
                handleWeatherChange(player);
                break;
            case "Tire Blowout":
                handleTireBlowout(player);
                break;
            default:
                System.out.println("Unknown event. No effect applied.");
        }
    }

    private void handlePoliceRoadblock(Car player) {
        System.out.println(player.getName() + " encounters a police roadblock!");
        System.out.print("Do you want to stop or attempt to break through? (stop/break): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("break")) {
            if (Math.random() > 0.5) {
                System.out.println("You successfully broke through the roadblock!");
            } else {
                System.out.println("You failed to break through and got caught! Losing a turn.");
                player.setTurnLost(true);
            }
        } else {
            System.out.println("You stopped at the roadblock and lost a turn.");
            player.setTurnLost(true);
        }
    }

    private void handleFuelLeak(Car player) {
        double fuelLost = player.getFuel() * 0.5;
        player.setFuel(player.getFuel() - fuelLost); // Deduct fuel
        System.out.printf("You lost %.2f gallons of fuel due to a leak!\n", fuelLost);
    }

    private void handleSpeedTrap(Car player) {
        double fine = 20; // Deduct 20 points from the player's score
        player.setScore(player.getScore() - fine);
        System.out.println("You were caught in a speed trap! Fine imposed: " + fine + " points.");
    }

    private void handleWeatherChange(Car player) {
        player.setWeatherMultiplier(1.2); // Increase fuel consumption by 20%
        System.out.println("Adverse weather conditions are increasing your fuel consumption!");
    }

    private void handleTireBlowout(Car player) {
        System.out.println("You had a tire blowout and lost a turn repairing it.");
        player.setTurnLost(true);
    }
}