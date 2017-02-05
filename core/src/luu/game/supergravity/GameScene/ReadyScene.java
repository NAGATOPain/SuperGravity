package luu.game.supergravity.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import luu.game.supergravity.GameComponent.Button;
import luu.game.supergravity.GameComponent.TextBox;
import luu.game.supergravity.GameConstants;
import luu.game.supergravity.GameObject.Background;
import luu.game.supergravity.GameObject.Ground;

/**
 * Created by luu on 9/28/16.
 */

public class ReadyScene extends Scene {

    private TextBox gameTitle;
    private Background background;
    private Ground ground;
    private Button playButton;
    private float stateTime; //The time loading from ready to play
    private boolean startTradition;

    public ReadyScene(GameSceneManager gsm) {
        super(gsm);
        gameTitle = new TextBox(GameConstants.OPEN_SANS_PATH, WORLD_HEIGHT, 1 / 10f, Color.DARK_GRAY);
        background = new Background(WORLD_WIDTH, WORLD_HEIGHT);
        ground = new Ground(WORLD_WIDTH, WORLD_HEIGHT);
        playButton = new Button(GameConstants.BUTTON_PLAY_PATH, WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f,
                WORLD_WIDTH * GameConstants.BUTTON_PLAY_SIZE, WORLD_WIDTH * GameConstants.BUTTON_PLAY_SIZE);
        stateTime = 0.0f;
        startTradition = false;
        backgroundMusic.play();
    }

    @Override
    public void update(float dt) {
        handleInput();
        background.update(dt);
        ground.update(dt);
        loadingToPlayScene(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.setColor(1f, 1f, 1f, 1f);
        batch.begin();
        background.render(batch);
        ground.render(batch);
        gameTitle.render(batch, "  SUPER \nGRAVITY",
                WORLD_WIDTH / 2f - gameTitle.getWidth("  SUPER \nGRAVITY") / 2f, WORLD_HEIGHT * 9 / 10);
        playButton.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        gameTitle.dispose();
        background.dispose();
        ground.dispose();
        playButton.dispose();
        backgroundMusic.dispose();
    }

    private void handleInput(){
        if (Gdx.input.justTouched()){
            if (playButton.isTouched(camera)) {
                startTradition = true;
            }
        }
    }

    private void loadingToPlayScene(float dt){
        if (startTradition){
            stateTime += dt;
        }
        if (stateTime >= GameConstants.TRADING_TIME){
            gsm.push(new PlayScene(gsm));
        }
    }
}
