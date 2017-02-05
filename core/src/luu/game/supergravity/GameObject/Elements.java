package luu.game.supergravity.GameObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by luu on 9/27/16.
 */

public abstract class Elements {
    protected float WORLD_WIDTH, WORLD_HEIGHT;

    public Elements(float WORLD_WIDTH, float WORLD_HEIGHT){
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
