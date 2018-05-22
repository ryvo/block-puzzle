package cz.ryvo.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import cz.ryvo.game.G001;
import cz.ryvo.game.config.Config;

import static cz.ryvo.game.config.Config.I18_BUTTON_START_LABEL;
import static cz.ryvo.game.config.Config.I18_GAME_NAME;

public class MainMenuScreen implements Screen {

    final G001 game;

    public MainMenuScreen(G001 game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.font.getData().setScale(4);
        game.font.draw(game.batch, game.bundle.get(I18_GAME_NAME), 100, 150);
        game.font.draw(game.batch, game.bundle.get(I18_BUTTON_START_LABEL), 100, 100);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
