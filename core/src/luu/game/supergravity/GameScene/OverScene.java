package luu.game.supergravity.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import luu.game.supergravity.GameComponent.Button;
import luu.game.supergravity.GameComponent.TextBox;
import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/28/16.
 */

public class OverScene extends Scene {

    private TextBox gameOverText, timeScore;
    private Texture whiteScorePanel;
    private Button btnRestart;
    private String timeAlive;
    private String highScore;
    private float stateTime; private boolean startTradition; //Loading to Ready Scene

    public OverScene(GameSceneManager gsm, float time){
        super(gsm);
        this.timeAlive = Integer.toString((int)time);
        gameOverText = new TextBox(GameConstants.OPEN_SANS_PATH, WORLD_HEIGHT, 1 / 12f, Color.WHITE);
        timeScore =  new TextBox(GameConstants.OPEN_SANS_PATH, WORLD_HEIGHT, 1/20f, Color.BLACK);
        whiteScorePanel = new Texture(Gdx.files.internal(GameConstants.WHITE_PANEL));
        btnRestart = new Button(GameConstants.BUTTON_RESTART_PATH, WORLD_WIDTH / 2f,
                WORLD_HEIGHT * 0.33f, WORLD_WIDTH * GameConstants.BUTTON_RESTART_SIZE,
                WORLD_WIDTH * GameConstants.BUTTON_RESTART_SIZE);
        highScore();
        backgroundMusic.pause();
        stateTime = 0.0f;
        startTradition = false;
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.justTouched()){
            if (btnRestart.isTouched(camera)) startTradition = true;
        }
        loadingToReadyScene(dt);
    }

    @Override
    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(camera.combined);
        batch.setColor(1f, 1f, 1f, 1f);
        batch.begin();
        gameOverText.render(batch, "GAME OVER!",
                WORLD_WIDTH / 2f - gameOverText.getWidth("GAME OVER!") / 2f, WORLD_HEIGHT * 19 / 20f);
        batch.draw(whiteScorePanel, WORLD_WIDTH *(0.5f - GameConstants.OVER_PANEL_SIZE / 2f),
                WORLD_HEIGHT * 0.45f, WORLD_WIDTH * GameConstants.OVER_PANEL_SIZE,
                WORLD_HEIGHT * (1 - GameConstants.OVER_PANEL_SIZE));
        //Render score :
        timeScore.render(batch, "TIME" , WORLD_WIDTH / 2f - timeScore.getWidth("TIME") / 2f, WORLD_HEIGHT * 0.77f);
        timeScore.render(batch, timeAlive, WORLD_WIDTH / 2f - timeScore.getWidth(timeAlive) / 2f
        , WORLD_HEIGHT * 0.77f - timeScore.getHeight(timeAlive) * 2f);
        //Render high score :
        timeScore.render(batch, "BEST" , WORLD_WIDTH / 2f - timeScore.getWidth("BEST") / 2f, WORLD_HEIGHT * 0.6f);
        timeScore.render(batch, highScore, WORLD_WIDTH / 2f - timeScore.getWidth(highScore) / 2f
                , WORLD_HEIGHT * 0.6f - timeScore.getHeight(highScore) * 2f);
        btnRestart.render(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        gameOverText.dispose();
        whiteScorePanel.dispose();
        timeScore.dispose();
        backgroundMusic.dispose();
    }

    private void highScore() {
        Preferences scoreSaver = Gdx.app.getPreferences("BestScore");
        scoreSaver.getString("Best", "0");
        highScore = scoreSaver.getString("Best").trim();
        if (highScore == "0" || highScore == ""){
            //Still not set score :
            highScore = timeAlive;
            scoreSaver.putString("Best", highScore);
        }
        else if (Integer.parseInt(highScore) < Integer.parseInt(timeAlive)) {
            highScore = timeAlive;
            //Write new record
            scoreSaver.putString("Best", highScore);
        }
        scoreSaver.flush();
    }

    private void loadingToReadyScene(float dt){
        if (startTradition){
            stateTime += dt;
        }
        if (stateTime >= GameConstants.TRADING_TIME){
            gsm.push(new ReadyScene(gsm));
        }
    }
}
