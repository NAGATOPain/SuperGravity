package luu.game.supergravity.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/26/16.
 */

public abstract class Scene {

    protected GameSceneManager gsm;
    protected float WORLD_WIDTH, WORLD_HEIGHT;
    protected OrthographicCamera camera;
    protected Music backgroundMusic;

    public Scene(GameSceneManager gsm){
        this.gsm = gsm;
        WORLD_WIDTH = Gdx.graphics.getWidth();
        WORLD_HEIGHT = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f);
        camera.update();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(GameConstants.BACKGROUND_MUSIC));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
