package carchasegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont; // Include this for font loading

public class CarChaseGame extends Game {
    public AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        
        // Load textures
        assetManager.load("assets/textures/player_car.png", Texture.class);
        assetManager.load("assets/textures/police_car.png", Texture.class);
        
        // Load sounds
        assetManager.load("assets/sounds/car_engine.wav", Sound.class);
        assetManager.load("assets/sounds/background_music.ogg", Music.class);
        
        // Load fonts (if you have any bitmap fonts)
        assetManager.load("assets/fonts/hud_font.fnt", BitmapFont.class);

        // Wait for all assets to finish loading (synchronous)
        assetManager.finishLoading();

        // Set the initial screen to GameScreen, passing the assetManager
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        assetManager.dispose(); // Dispose all assets when the game is closed
    }
}