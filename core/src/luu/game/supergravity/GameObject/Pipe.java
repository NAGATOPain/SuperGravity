package luu.game.supergravity.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/27/16.
 */

public class Pipe extends Elements {

    private Texture imgPipe;
    private float x;
    private Vector2 pipeSize;

    public Pipe(float WORLD_WIDTH, float WORLD_HEIGHT, float currentY, float deltaY) {
        super(WORLD_WIDTH, WORLD_HEIGHT);
        imgPipe = new Texture(Gdx.files.internal(GameConstants.PIPE_PATH));
        Random r = new Random();
        float width = WORLD_WIDTH * GameConstants.CHARACTER_SIZE_WIDTH * (1 + r.nextFloat());
        float height = currentY + (r.nextFloat() - 0.5f) * deltaY * 2f;
        while (height < 0 || height > WORLD_HEIGHT * GameConstants.MAX_HEIGHT_OF_PIPES)
            height = currentY + (r.nextFloat() - 0.5f) * deltaY * 2f;
        pipeSize = new Vector2(width, height);
        x = WORLD_WIDTH;
    }

    @Override
    public void update(float dt) {
        x -= (WORLD_WIDTH * dt) / GameConstants.TIME_PER_BACKGROUND;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(imgPipe, x, 0f , pipeSize.x, pipeSize.y + WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT);
    }

    @Override
    public void dispose() {
        imgPipe.dispose();
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return pipeSize.y + WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT;
    }
    public Vector2 getSize(){
        return this.pipeSize;
    }
}
