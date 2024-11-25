package carchasegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerCar {
    private float x, y, speed;
    private float fuel; // Current fuel in gallons
    private final float fuelCapacity = 15; // 15 gallons per tank
    private final float milesPerGallon = 25; // 25 miles per gallon
    private Texture carTexture;

    public PlayerCar(Texture carTexture) {
        this.carTexture = carTexture;
        this.x = 100;
        this.y = 100;
        this.speed = 200; // Speed in pixels per second
        this.fuel = fuelCapacity; // Start with a full tank
    }

    public void update(float deltaTime) {
        if (fuel > 0) {
            // Move the car forward and deduct fuel based on distance covered
            y += speed * deltaTime;
            fuel -= (speed * deltaTime / milesPerGallon);
            if (fuel < 0) {
                fuel = 0; // Prevent fuel from going negative
            }
        }
    }

    public float getDistanceCovered(float deltaTime) {
        return fuel > 0 ? speed * deltaTime : 0; // Distance covered based on speed and time
    }

    public float getMilesPerTank() {
        return fuelCapacity * milesPerGallon; // Total distance the car can travel on a full tank
    }

    public void refillFuel() {
        this.fuel = fuelCapacity; // Refills the fuel tank to full capacity
    }

    public void render(SpriteBatch batch) {
        batch.draw(carTexture, x, y); // Draw the car texture at its current position
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, carTexture.getWidth(), carTexture.getHeight());
    }

    public float getFuel() {
        return fuel; // Returns the current fuel level
    }

    public float getSpeed() {
        return speed; // Returns the car's speed
    }

    public void dispose() {
        carTexture.dispose(); // Dispose of the texture to free resources
    }
}