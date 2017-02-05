package luu.game.supergravity.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import luu.game.supergravity.GameComponent.Animator;
import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/27/16.
 */

public class MainCharacter extends Elements {

    private Animator jumpAnimator, runAnimator, staticAnimator, animator;
    private Vector2 characterXY, characterSize;
    private volatile int status;
    private float fallingPlace, gravity, fallingTime, stateTime;
    private volatile boolean canJump;
    private Sound jumpSound;
    private Random r; //For change gravity

    public MainCharacter(float WORLD_WIDTH, float WORLD_HEIGHT) {
        super(WORLD_WIDTH, WORLD_HEIGHT);
        jumpAnimator = new Animator(GameConstants.JUMP_TEXTURE_PATH, "jump", 4, 0.1f);
        runAnimator = new Animator(GameConstants.RUN_TEXTURE_PATH, "run", 6, 0.1f);
        staticAnimator = new Animator(GameConstants.STATIC_TEXTURE_PATH, "static", 1, 0.1f);

        animator = staticAnimator;
        status = GameConstants.IS_STATIC;

        characterXY = new Vector2(WORLD_WIDTH / 10f, WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT);
        characterSize = new Vector2(WORLD_WIDTH * GameConstants.CHARACTER_SIZE_WIDTH,
                WORLD_WIDTH * GameConstants.CHARACTER_SIZE_HEIGHT );
        fallingPlace = WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT;
        gravity = 10.0f;
        fallingTime = 0.0f;
        stateTime = 0.0f;
        canJump = true;

        jumpSound = Gdx.audio.newSound(Gdx.files.internal(GameConstants.JUMP_SOUND));
        r = new Random();
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        if (stateTime >= 5.0f){ //5.0 seconds to change gravity
            stateTime = 0.0f;
            gravity = 5.0f + r.nextFloat() * 20.0f; //gravity is between 5.0f to 25.0f
        }
        if (animator.isAnimating()){
            //Main character is acting:
            animator.animating(dt);
            switch (status){
                case GameConstants.IS_JUMPING :
                    jumping(dt);
                    break;
                case GameConstants.IS_RUNNING :
                    running(dt);
                    break;
                case GameConstants.IS_STATIC :

                    break;
                default:
                    break;
            }
        }
        else {
            animator = staticAnimator;
            status = GameConstants.IS_STATIC;
        }
        if (characterXY.y > fallingPlace){
            //Falling :
            falling(dt);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(animator.getCurrentFrame(), characterXY.x, characterXY.y, characterSize.x, characterSize.y);
    }

    @Override
    public void dispose() {
        staticAnimator.dispose();
        jumpAnimator.dispose();
        runAnimator.dispose();
        animator.dispose();
        jumpSound.dispose();
    }

    private void falling(float dt){
        animator = jumpAnimator;
        status = GameConstants.IS_JUMPING;
        fallingTime += dt;
        float buffer = characterXY.y - gravity * fallingTime * fallingTime * 1.25f;
        if (buffer <= fallingPlace) {
            //End of falling
            characterXY.y = fallingPlace;
            canJump = true; //If character is on pipe or ground, reset canJump
        }
        else characterXY.y = buffer;
    }

    private void jumping(float dt){
        fallingTime = 0.0f;
        characterXY.y += dt * (WORLD_HEIGHT / 2.0f) * (15.0f / gravity);
        //Character can jump half of screen on gravity 15
    }

    private void running(float dt){
        if (characterXY.x < WORLD_WIDTH * 0.9f)
             characterXY.x += dt * WORLD_WIDTH * 0.25f;
    }

    public Vector2 getCharacterXY(){
        return this.characterXY;
    }

    public void setStatus(int status){
        this.status = status;
        switch (status){
            case GameConstants.IS_JUMPING :
                if (canJump) { //If character is on pipe or ground
                    animator = jumpAnimator;
                    animator.resume();
                    jumpSound.play();
                    canJump = false; //Can't jump next
                }
                break;
            case GameConstants.IS_RUNNING :
                animator = runAnimator;
                animator.resume();
                break;
            case GameConstants.IS_STATIC :
                animator = staticAnimator;
                animator.resume();
                break;
            default:
                break;
        }
    }

    public void setFallingPlace(float newFallingPlace){
        this.fallingPlace = newFallingPlace;
    }

    public float getWidth(){
        return WORLD_WIDTH * GameConstants.CHARACTER_SIZE_WIDTH;
    }

    public float getGravity(){
        return this.gravity;
    }
}
