package luu.game.supergravity.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.text.DecimalFormat;
import java.util.Vector;

import luu.game.supergravity.GameComponent.TextBox;
import luu.game.supergravity.GameConstants;
import luu.game.supergravity.GameObject.Background;
import luu.game.supergravity.GameObject.Ground;
import luu.game.supergravity.GameObject.MainCharacter;
import luu.game.supergravity.GameObject.Pipe;

/**
 * Created by luu on 9/26/16.
 */

public class PlayScene extends Scene {

    private volatile boolean isPaused, gameOver;

    private MainCharacter mainCharacter;
    private Background background;
    private Ground ground;
    private Vector<Pipe> pipes;
    private TextBox gravity, timeAlive;
    private float stateTime;
    private DecimalFormat decimalFormat;
    private float traditionTime; private boolean startTradition; //Traditing to OverScene
    private Sound deathSound;

    public PlayScene(GameSceneManager gsm) {
        super(gsm);
        initGameComponent();
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (!isPaused){
            //Update here
            stateTime += dt;
            background.update(dt);
            ground.update(dt);
            if (stateTime >= 5f) { //5 seconds to start game
                for (int i = 0; i < pipes.size(); i++) {
                    pipes.elementAt(i).update(dt);
                    //If main character is upon a pipe:
                    if ((mainCharacter.getCharacterXY().x + mainCharacter.getWidth()) >= pipes.elementAt(i).getX()
                            && mainCharacter.getCharacterXY().x <= (pipes.elementAt(i).getX() + pipes.elementAt(i).getSize().x)) {
                        //Character is on the pipe
                        if (mainCharacter.getCharacterXY().y >= pipes.elementAt(i).getY()) {
                            //Character.y is upon than pipe.y
                            mainCharacter.setFallingPlace(pipes.elementAt(i).getY());
                        } else {
                            //Character is falling to ground, death
                            mainCharacter.setFallingPlace(WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT);
                            isPaused = true;
                            gameOver = true;
                        }
                    }
                }
                //Add new pipe :
                if (pipes.lastElement().getX() <= WORLD_WIDTH * GameConstants.DELTA_X_OF_PIPES) {
                    pipes.addElement(new Pipe(WORLD_WIDTH, WORLD_HEIGHT,
                            pipes.lastElement().getSize().y, WORLD_HEIGHT * GameConstants.DELTA_Y_OF_PIPES));

                }
                //Delete first pipe if it's overflow
                if (pipes.firstElement().getX() <= -pipes.firstElement().getSize().x){
                    pipes.removeElementAt(0);
                }
            }
            mainCharacter.update(dt);
        }
        else {
            //User pause:
        }
        if (gameOver){
            overGame(dt);
            if (startTradition){
                traditionTime += dt;
                if (traditionTime >= GameConstants.TRADING_TIME){
                    //End of tradition
                    gsm.push(new OverScene(gsm, stateTime));
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.render(batch);
        for (int i = 0; i < pipes.size(); i++){
            pipes.elementAt(i).render(batch);
        }
        ground.render(batch);
        if (!startTradition) { //Don't show this texts when end game
            gravity.render(batch, "G: " + decimalFormat.format(mainCharacter.getGravity()),
                    WORLD_WIDTH * GameConstants.GRAVITY_X, WORLD_HEIGHT * GameConstants.GRAVITY_Y);
            timeAlive.render(batch, "" + (int) stateTime,
                    WORLD_WIDTH * GameConstants.TIME_X, WORLD_HEIGHT * GameConstants.TIME_Y);
        }
        else {
            batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        }
        mainCharacter.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        mainCharacter.dispose();
        for (int i = 0; i < pipes.size(); i++){
            pipes.elementAt(i).dispose();
        }
        gravity.dispose();
        timeAlive.dispose();
        deathSound.dispose();
        backgroundMusic.dispose();
    }

    private void initGameComponent(){
        mainCharacter = new MainCharacter(WORLD_WIDTH, WORLD_HEIGHT);
        background = new Background(WORLD_WIDTH, WORLD_HEIGHT);
        ground = new Ground(WORLD_WIDTH, WORLD_HEIGHT);
        pipes = new Vector<Pipe>();
        pipes.addElement(new Pipe(WORLD_WIDTH, WORLD_HEIGHT, mainCharacter.getWidth() * 1.25f , 0f));
        isPaused = false;
        gameOver = false;
        gravity = new TextBox(GameConstants.OPEN_SANS_PATH, WORLD_HEIGHT, 1/20f, Color.RED);
        timeAlive = new TextBox(GameConstants.OPEN_SANS_PATH, WORLD_HEIGHT, 1/20f, Color.RED);
        stateTime = 0.0f;
        decimalFormat = new DecimalFormat("##.#");
        traditionTime = 0.0f;
        startTradition = false;
        deathSound = Gdx.audio.newSound(Gdx.files.internal(GameConstants.DEATH_SOUND));
        backgroundMusic.play();
    }

    private void handleInput(){
        if (!gameOver) {
            if (Gdx.input.justTouched()) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
                touchPos = camera.unproject(touchPos);
                if (touchPos.x >= WORLD_WIDTH * 0.75f){
                    //Click on the right side, is running
                    mainCharacter.setStatus(GameConstants.IS_RUNNING);
                }
                else {
                    mainCharacter.setStatus(GameConstants.IS_JUMPING);
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                mainCharacter.setStatus(GameConstants.IS_JUMPING);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                mainCharacter.setStatus(GameConstants.IS_RUNNING);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                isPaused = true;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                isPaused = false;
            }
        }
    }

    private void overGame(float dt){
        mainCharacter.setFallingPlace(WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT);
        if (mainCharacter.getCharacterXY().y > (WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT)){
            //Make character falls after die
            mainCharacter.update(dt);
        }
        else {
            //Game Over
            startTradition = true;
            deathSound.play();
        }
    }
}
