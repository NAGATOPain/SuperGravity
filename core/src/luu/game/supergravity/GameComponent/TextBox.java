package luu.game.supergravity.GameComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by luu on 9/27/16.
 */

public class TextBox {

    private BitmapFont font;
    private GlyphLayout layout;

    public TextBox(String PATH, float WORLD_HEIGHT, float FONT_SIZE, Color color){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = color;
        parameter.size = (int) (WORLD_HEIGHT * FONT_SIZE);
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = (int) (WORLD_HEIGHT * FONT_SIZE / 20f);
        font = generator.generateFont(parameter);
        generator.dispose();
        layout = new GlyphLayout();
    }

    public void render(SpriteBatch batch, String str, float x, float y){
        font.draw(batch, str, x, y);
    }

    public float getWidth(String str){
        layout.setText(font, str);
        return layout.width;
    }

    public float getHeight(String str){
        layout.setText(font, str);
        return layout.height;
    }

    public void dispose(){
        font.dispose();
    }
}
