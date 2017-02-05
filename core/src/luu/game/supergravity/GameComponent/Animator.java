package luu.game.supergravity.GameComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by luu on 9/27/16.
 */
public class Animator {

    private Animation animation;
    private TextureAtlas atlas;
    private TextureRegion regions[], currentFrame;
    private float stateTime, timePerFrame;
    private int numOfFrames;

    public Animator(String PATH, String NAME, int numOfFrames, float timePerFrame){
        atlas = new TextureAtlas(Gdx.files.internal(PATH));
        this.numOfFrames = numOfFrames;
        this.timePerFrame = timePerFrame;
        regions = new TextureRegion[this.numOfFrames];
        for (int i = 0; i < numOfFrames; i++){
            regions[i] = atlas.findRegion(NAME + i); // Ex : jump1, jump2
            regions[i].getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        animation = new Animation(timePerFrame, regions);
        stateTime = 0.0f;
        currentFrame = animation.getKeyFrame(0f);
    }

    public void animating(float dt){
        stateTime += dt;
        currentFrame = animation.getKeyFrame(stateTime, true);
    }

    public boolean isAnimating(){
        if (stateTime < timePerFrame * numOfFrames) return true;
        return false;
    }

    public void resume(){
        stateTime = 0.0f;
    }

    public TextureRegion getCurrentFrame(){
        return this.currentFrame;
    }

    public void dispose(){
        atlas.dispose();
    }
}
