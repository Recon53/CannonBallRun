package carchasegame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
    private final BitmapFont font;

    public HUD() {
        // Initialize the font
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch, PlayerCar playerCar, float distanceRemaining) {
        // HUD layout positions
        int xPosition = 10;
        int yPositionStart = 70;
        int yOffset = 20;

        // Draw HUD text
        font.draw(batch, "Distance to LA: " + (int) distanceRemaining + " miles", xPosition, yPositionStart);
        font.draw(batch, "Miles per tank: " + (int) playerCar.getMilesPerTank() + " miles", xPosition, yPositionStart - yOffset);
        font.draw(batch, "Current fuel: " + (int) playerCar.getFuel() + " gallons", xPosition, yPositionStart - 2 * yOffset);
    }

    public void dispose() {
        // Dispose of font resources
        font.dispose();
    }
}