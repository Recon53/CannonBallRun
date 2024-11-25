package carchasegame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
    private CarChaseGame game;
    private SpriteBatch batch;
    private PlayerCar playerCar;
    private Array<PoliceCar> policeCars;
    private Array<Rectangle> gasStations;
    private HUD hud;

    // Sounds
    private Sound engineSound;
    private Music backgroundMusic;

    private static final float TOTAL_DISTANCE = 2700f;
    private float distanceRemaining = TOTAL_DISTANCE;

    public GameScreen(CarChaseGame game) {
        this.game = game;
        batch = new SpriteBatch();
        hud = new HUD();

        // Access assets from the AssetManager in CarChaseGame
        Texture playerTexture = game.assetManager.get("assets/textures/player_car.png", Texture.class);
        Texture policeTexture = game.assetManager.get("assets/textures/police_car.png", Texture.class);
        engineSound = game.assetManager.get("assets/sounds/car_engine.wav", Sound.class);
        backgroundMusic = game.assetManager.get("assets/sounds/background_music.ogg", Music.class);

        // Initialize player car and police cars
        playerCar = new PlayerCar(playerTexture);
        policeCars = new Array<>();
        policeCars.add(new PoliceCar(policeTexture, 400, 200));

        // Initialize gas stations
        gasStations = new Array<>();
        initializeGameObjects();

        // Play background music in a loop
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    private void initializeGameObjects() {
        // Example: initialize gas stations at specific coordinates
        gasStations.add(new Rectangle(800, 300, 50, 50));
        // Add more gas stations as needed
    }

    @Override
    public void render(float delta) {
        updateGame(delta);
        renderGame();
        checkWinOrLoseCondition();
    }

    private void updateGame(float delta) {
        playerCar.update(delta);
        distanceRemaining -= playerCar.getDistanceCovered(delta);

        // Check refueling
        checkRefueling();
    }

    private void checkRefueling() {
        for (Rectangle gasStation : gasStations) {
            if (playerCar.getBounds().overlaps(gasStation)) {
                playerCar.refillFuel();
                engineSound.play(); // Play engine sound when refilling fuel
                break;
            }
        }
    }

    private void checkWinOrLoseCondition() {
        if (distanceRemaining <= 0) {
            System.out.println("Congratulations! You've reached Los Angeles!");
        } else if (playerCar.getFuel() <= 0 && !isNearGasStation()) {
            System.out.println("Game Over - You ran out of fuel with no gas station nearby!");
        }
    }

    private boolean isNearGasStation() {
        for (Rectangle gasStation : gasStations) {
            if (playerCar.getBounds().overlaps(gasStation)) {
                return true;
            }
        }
        return false;
    }

    private void renderGame() {
        batch.begin();
        playerCar.render(batch);
        for (PoliceCar policeCar : policeCars) {
            policeCar.render(batch);
        }
        hud.render(batch, playerCar, distanceRemaining); // Render HUD
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hud.dispose();
        backgroundMusic.stop();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void show() {}
}