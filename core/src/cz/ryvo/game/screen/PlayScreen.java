package cz.ryvo.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.ryvo.game.G001;
import cz.ryvo.game.tile.BlockPosition;
import cz.ryvo.game.tile.ShapeColour;
import cz.ryvo.game.tile.Shape;
import cz.ryvo.game.tile.ShapeFactory;

public class PlayScreen extends ActorGestureListener implements Screen {

    final G001 game;

    private Stage stage;

    private List<Shape> shapes;

    private Image image;

    private ActorGestureListener listener;

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

        ShapeFactory factory = new ShapeFactory(game.assets);

        shapes = new ArrayList<Shape>();
        shapes.add(factory.createShape(blockPositions, ShapeColour.RED));

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        image = new Image(game.assets.get("sand.png", Texture.class));

        for (Shape shape : shapes) {
            stage.addActor(shape);
            shape.setPosition(32, 32);
        }
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.camera.combined);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.assets.get("sand.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //shape.render(game.batch, 64, 128);
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
