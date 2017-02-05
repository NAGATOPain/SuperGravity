package luu.game.supergravity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import luu.game.supergravity.GameScene.GameSceneManager;
import luu.game.supergravity.GameScene.ReadyScene;

public class MainGame extends ApplicationAdapter {

	private GameSceneManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		gsm = new GameSceneManager();
		gsm.push(new ReadyScene(gsm));
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}

	@Override
	public void render () {
		gsm.peek().update(Gdx.graphics.getDeltaTime());
		gsm.peek().render(batch);
	}
	
	@Override
	public void dispose () {
		gsm.pop();
		batch.dispose();
	}
}
