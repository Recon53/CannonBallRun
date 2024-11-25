package entities;

public class StateTransitionHelper {
    private static final String[] STATES = {
        "Florida", "Alabama", "Mississippi", "Louisiana",
        "Texas", "New Mexico", "Arizona", "California"
    };

    private static final int[] STATE_BOUNDARIES = {
        2800, 2400, 2000, 1600, 1200, 800, 400, 0
    };

    private static String lastState = STATES[0]; // Start with Florida

    public static void resetStateTracking() {
        lastState = STATES[0]; // Reset to the initial state
    }

    public static void notifyStateChange(double currentDistance, double previousDistance) {
        System.out.println("[DEBUG] Current Distance: " + currentDistance + ", Previous Distance: " + previousDistance);

        for (int i = 0; i < STATE_BOUNDARIES.length - 1; i++) {
            System.out.println("[DEBUG] Checking Boundary for: " + STATES[i] + " at " + STATE_BOUNDARIES[i]);
            // Detect boundary crossing
            if (previousDistance > STATE_BOUNDARIES[i] && currentDistance <= STATE_BOUNDARIES[i]) {
                String currentState = STATES[i];
                System.out.println("You are now leaving the state of " + lastState + "!");
                System.out.println("Welcome to the state of " + currentState + "!");
                lastState = currentState; // Update to the current state
                return; // Exit after detecting the first transition
            }
        }

        System.out.println("[DEBUG] No state transition detected.");
    }
}