package luu.game.supergravity.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/27/16.
 */

public class Background extends Elements {

    private Texture imgBackground;
    private float x;

    public Background(float WORLD_WIDTH, float WORLD_HEIGHT) {
        super(WORLD_WIDTH, WORLD_HEIGHT);
        imgBackground = new Texture(Gdx.files.internal(GameConstants.BACKGROUND_PATH));
        x = 0.0f;
    }

    @Override
    public void update(float dt) {
        x -= (WORLD_WIDTH * dt) / GameConstants.TIME_PER_BACKGROUND;
        if (x <= - WORLD_WIDTH) x = 0.0f;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(imgBackground, x, WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT
                , WORLD_WIDTH, WORLD_HEIGHT * (1 - 1 * GameConstants.GROUND_DT_HEIGHT));
        batch.draw(imgBackground, x + WORLD_WIDTH, WORLD_HEIGHT * GameConstants.GROUND_DT_HEIGHT
                , WORLD_WIDTH, WORLD_HEIGHT * (1 - 1 * GameConstants.GROUND_DT_HEIGHT));
    }

    @Override
    public void dispose() {
        imgBackground.dispose();
    }
}
