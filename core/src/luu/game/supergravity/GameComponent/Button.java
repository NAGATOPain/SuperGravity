package luu.game.supergravity.GameComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import luu.game.supergravity.GameConstants;

/**
 * Created by luu on 9/28/16.
 */

public class Button {

    private Texture btnRaw, btnPressed, btn;
    private float x, y, width, height;
    private Sound clickSound;

    public Button(String PATH, float x, float y, float width, float height){
        btnRaw = new Texture(Gdx.files.internal(PATH + "/btn_raw.png"));
        btnPressed = new Texture(Gdx.files.internal(PATH + "/btn_pressed.png"));
        btn = btnRaw;
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        clickSound = Gdx.audio.newSound(Gdx.files.internal(GameConstants.CLICKING_SOUND));
    }

    public boolean isTouched(OrthographicCamera camera){
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
        touchPos = camera.unproject(touchPos);
        if (touchPos.x >= (x - width / 2f) && touchPos.x <= (x + width / 2f)){
            if (touchPos.y >= (y - height / 2f) && touchPos.y <= (y + height / 2f)){
                btn = btnPressed;
                clickSound.play();
                return true;
            }
        }
        return false;
    }

    public void render(SpriteBatch batch){
        btn.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        batch.draw(btn, x - width / 2f, y - height / 2f, width, height);
    }

    public void dispose(){
        btn.dispose();
        btnPressed.dispose();
        btnRaw.dispose();
        clickSound.dispose();
    }
}
