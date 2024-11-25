package carchasegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PoliceCar {
    private float x, y, speed;
    private float fuel; // Current fuel in gallons
    private final float fuelCapacity = 15; // 15 gallons per tank
    private final float milesPerGallon = 25; // 25 miles per gallon
    private Texture policeTexture;

    public PoliceCar(Texture policeTexture, float startX, float startY) {
        this.policeTexture = policeTexture;
        this.x = startX;
        this.y = startY;
        this.fuel = fuelCapacity; // Start with a full tank
        this.speed = 250; // Police car speed in pixels per second
    }

    public void update(float deltaTime, PlayerCar playerCar) {
        if (fuel > 0) {
            // Move toward the player's position
            if (playerCar.getY() > y) y += speed * deltaTime;
            if (playerCar.getX() > x) x += speed * deltaTime;
            if (playerCar.getX() < x) x -= speed * deltaTime;

            // Deduct fuel based on movement
            fuel -= (speed * deltaTime / milesPerGallon);
            if (fuel < 0) {
                fuel = 0; // Prevent fuel from going negative
            }
        }
    }

    public void refillFuel() {
        this.fuel = fuelCapacity; // Refills the fuel tank to full capacity
    }

    public void render(SpriteBatch batch) {
        batch.draw(policeTexture, x, y); // Draw the police car at its current position
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, policeTexture.getWidth(), policeTexture.getHeight());
    }

    public float getFuel() {
        return fuel; // Return the current fuel level
    }

    public float getSpeed() {
        return speed; // Return the speed of the police car
    }

    public void dispose() {
        policeTexture.dispose(); // Dispose of the texture to free resources
    }
}