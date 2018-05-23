package cz.ryvo.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.ryvo.game.G001;
import cz.ryvo.game.block.BlockPosition;
import cz.ryvo.game.block.PlayingBoard;
import cz.ryvo.game.block.ShapeColour;
import cz.ryvo.game.block.Shape;
import cz.ryvo.game.block.ShapeFactory;
import cz.ryvo.game.level.Level;
import cz.ryvo.game.stage.PlayStage;

public class PlayScreen implements Screen {

    final G001 game;

    private PlayStage stage;

    private List<Shape> shapes;

    private Image image;

    private ActorGestureListener listener;

    private Level level;

    private PlayingBoard playingBoard;

    public PlayScreen(G001 game) {
        this.game = game;

        int[] a = {
                1,1,0,0,0,
                1,0,0,0,1,
                1,0,1,1,1,
                1,1,1,0,1,
        };
        Set<BlockPosition> blockPositions = new HashSet();
        blockPositions.add(new BlockPosition(0, 0));
        blockPositions.add(new BlockPosition(1, 0));
        blockPositions.add(new BlockPosition(0, 1));
        blockPositions.add(new BlockPosition(0, 2));

        FitViewport viewport = new FitViewport(Gdx.app.getGraphics().getWidth(), Gdx.graphics.getHeight(), game.camera);
        this.stage = new PlayStage(viewport);

        ShapeFactory factory = new ShapeFactory(game.config.getBlockSize(), game.assets, stage);

        shapes = new ArrayList<Shape>();
        shapes.add(factory.createShape(blockPositions, ShapeColour.RED));


        Gdx.input.setInputProcessor(stage);
        image = new Image(game.assets.get("sand.png", Texture.class));

        for (Shape shape : shapes) {
            stage.addActor(shape);
            shape.setPosition(32, 32);
            shape.getColor().a = 0.5f;
        }
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.camera.combined);
        level = new Level("levels/level-001.json");
        playingBoard = new PlayingBoard(game.config.getBlockSize(), level.getBoard());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.assets.get("sand.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //shape.render(game.batch, 64, 128);
        game.batch.draw(playingBoard, 100, 300);
        game.batch.end();

        stage.draw();
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
