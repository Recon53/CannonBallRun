package game;

import entities.Car;
import entities.PoliceCar;
import entities.RouteType;
import entities.SUV;
import entities.SportsCar;
import entities.StateTransitionHelper;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CannonBallRun {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Car player1 = chooseCar(scanner, "Player 1");
        Car player2 = chooseCar(scanner, "Player 2");

        RouteType route1 = chooseRoute(scanner, "Player 1");
        RouteType route2 = chooseRoute(scanner, "Player 2");

        PoliceCar police = new PoliceCar(2800, 20, 15);

        System.out.println("------ CannonBallRun ------");
        while (player1.getDistanceRemaining() > 0 && player2.getDistanceRemaining() > 0) {
            displayGameState(player1, player2, police);

            if (!player1.isTurnLost()) {
                playerTurn(scanner, player1, route1);
            } else {
                System.out.println(player1.getName() + " lost a turn due to a random event!");
                player1.setTurnLost(false);
            }

            if (!player2.isTurnLost()) {
                playerTurn(scanner, player2, route2);
            } else {
                System.out.println(player2.getName() + " lost a turn due to a random event!");
                player2.setTurnLost(false);
            }

            policeTurn(police);
            System.out.println("------ CannonBallRun ------");
        }

        endGame(player1, player2);
    }

    private static Car chooseCar(Scanner scanner, String playerName) {
        while (true) {
            System.out.println(playerName + ", choose your car: 1 for Sports Car, 2 for SUV");
            try {
                int choice = scanner.nextInt();
                if (choice == 1) {
                    return new SportsCar(playerName, 2800, 40.0);
                } else if (choice == 2) {
                    return new SUV(playerName, 2800, 45.0);
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Sports Car or 2 for SUV.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 for Sports Car, 2 for SUV).");
                scanner.next();
            }
        }
    }

    private static RouteType chooseRoute(Scanner scanner, String playerName) {
        while (true) {
            System.out.println(playerName + ", choose your route: 1 for Main Route, 2 for Alternate Route");
            try {
                int choice = scanner.nextInt();
                if (choice == 1) {
                    return RouteType.MAIN;
                } else if (choice == 2) {
                    return RouteType.ALTERNATE;
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Main Route or 2 for Alternate Route.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 for Main Route, 2 for Alternate Route).");
                scanner.next();
            }
        }
    }

    private static void playerTurn(Scanner scanner, Car player, RouteType route) {
        System.out.println(player.getName() + ", it's your turn.");
        System.out.println(player.getName() + " - Fuel: " + String.format("%.2f", player.getFuel()) + " gallons.");

        double previousDistance = player.getDistanceRemaining();

        if (player.getFuel() < 10.0) {
            System.out.println("⚠️ Warning: Fuel is running low (below 10 gallons). Do you want to refuel before continuing? (yes/no): ");
            String choice = scanner.next().trim().toLowerCase();
            if (choice.equals("yes")) {
                player.setFuel(20);
                System.out.println(player.getName() + " refueled to a full tank!");
            } else {
                System.out.println("Continuing without refueling. Be cautious!");
            }
        }

        System.out.print("Enter a valid distance to drive (miles): ");
        double miles = scanner.nextDouble();
        while (!player.drive(miles, route)) {
            System.out.println("You need " + String.format("%.2f", player.fuelNeededFor(miles)) +
                    " gallons for this trip, but you only have " + String.format("%.2f", player.getFuel()) + " gallons.");
            System.out.print("Please refuel or choose a shorter distance: ");
            miles = scanner.nextDouble();
        }

        System.out.println(player.getName() + " drove " + String.format("%.2f", miles) + " miles!");

        // Check for state transition
        StateTransitionHelper.notifyStateChange(player.getDistanceRemaining(), previousDistance);

        System.out.println(player.getName() + " - Distance Remaining: " +
                String.format("%.2f", player.getDistanceRemaining()) + " miles, Fuel: " +
                String.format("%.2f", player.getFuel()) + " gallons.");
    }

    private static void policeTurn(PoliceCar police) {
        double milesDriven = police.move();
        System.out.println("🚔 Police moved " + String.format("%.2f", milesDriven) + " miles.");
    }

    private static void endGame(Car player1, Car player2) {
        System.out.println("Game Over! Final Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " points");
        System.out.println(player2.getName() + ": " + player2.getScore() + " points");
    }

    private static void displayGameState(Car player1, Car player2, PoliceCar police) {
        System.out.println(player1.getName() + " - Distance Remaining: " +
                String.format("%.2f", player1.getDistanceRemaining()) + " miles, Fuel: " +
                String.format("%.2f", player1.getFuel()) + " gallons");
        System.out.println(player2.getName() + " - Distance Remaining: " +
                String.format("%.2f", player2.getDistanceRemaining()) + " miles, Fuel: " +
                String.format("%.2f", player2.getFuel()) + " gallons");
        System.out.println("Police - Distance Remaining: " +
                String.format("%.2f", police.getDistanceRemaining()) + " miles, Fuel: " +
                String.format("%.2f", police.getFuel()) + " gallons");
    }
}