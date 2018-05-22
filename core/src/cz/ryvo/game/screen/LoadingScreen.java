package cz.ryvo.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import cz.ryvo.game.G001;

public class LoadingScreen implements Screen {

    final G001 game;

    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(G001 game) {
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("Assets loading started");
        shapeRenderer.setProjectionMatrix(game.camera.combined);
        this.progress = 0f;
    }

    public void update(float delta) {
        progress = MathUtils.lerp(progress, game.assets.getProgress(), 0.5f);
        if (game.assets.update() && progress > game.assets.getProgress() - 0.001f) {
            System.out.println("Assets loading finished");
            game.handleAssetsLoaded();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, game.camera.viewportHeight / 2 - 8, game.camera.viewportWidth - 64, 16);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, game.camera.viewportHeight / 2 - 8, progress * (game.camera.viewportWidth - 64), 16);
        shapeRenderer.end();
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
        shapeRenderer.dispose();
    }
}
