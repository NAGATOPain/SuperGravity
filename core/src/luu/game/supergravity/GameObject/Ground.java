package luu.game.supergravity.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/27/16.
 */

public class Ground extends Elements {

    private Texture imgGround;
    private float x;

    public Ground(float WORLD_WIDTH, float WORLD_HEIGHT) {
        super(WORLD_WIDTH, WORLD_HEIGHT);
        imgGround = new Texture(Gdx.files.internal(GameConstants.GROUND_PATH));
        x = 0.0f;
    }

    @Override
    public void update(float dt) {
        x -= (WORLD_WIDTH * dt) / GameConstants.TIME_PER_BACKGROUND;
        if (x <= - WORLD_WIDTH) x = 0.0f;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(imgGround, x, 0f, WORLD_WIDTH, WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT);
        batch.draw(imgGround, x + WORLD_WIDTH, 0f, WORLD_WIDTH, WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT);
    }

    @Override
    public void dispose() {
        imgGround.dispose();
    }
}
