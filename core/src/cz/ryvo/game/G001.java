package cz.ryvo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader.I18NBundleParameter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

import cz.ryvo.game.config.Config;
import cz.ryvo.game.listener.LoadAssetsListener;
import cz.ryvo.game.screen.LoadingScreen;
import cz.ryvo.game.screen.MainMenuScreen;
import cz.ryvo.game.screen.PlayScreen;

public class G001 extends Game implements LoadAssetsListener {

    public OrthographicCamera camera;
    public AssetManager assets;
    public I18NBundle bundle;
    public SpriteBatch batch;
    public BitmapFont font;
    public Config config;

    public LoadingScreen loadingScreen;

	@Override
	public void create () {
        config = Config.getInstance();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        font = new BitmapFont();

        assets = new AssetManager();
		queueAssets();

		loadingScreen = new LoadingScreen(this);
		this.setScreen(loadingScreen);
	}

	@Override
	public void render () {
		super.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		assets.dispose();
	}

	private void queueAssets() {
		assets.load("i18n/bundle", I18NBundle.class, new I18NBundleParameter(Locale.getDefault()));
		assets.load("sand.png", Texture.class);
		assets.load("block-aqua.png", Texture.class);
		assets.load("block-blue.png", Texture.class);
		assets.load("block-green.png", Texture.class);
		assets.load("block-magenta.png", Texture.class);
		assets.load("block-orange.png", Texture.class);
		assets.load("block-red.png", Texture.class);
		assets.load("block-yellow.png", Texture.class);
	}

	@Override
	public void handleAssetsLoaded() {
		bundle = assets.get("i18n/bundle", I18NBundle.class);
		//setScreen(new MainMenuScreen(this));
		setScreen(new PlayScreen(this));
	}
}
