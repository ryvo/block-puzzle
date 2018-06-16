package cz.ryvo.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

import cz.ryvo.game.G001;
import cz.ryvo.game.block.PlayBoard;
import cz.ryvo.game.block.Shape;
import cz.ryvo.game.block.ShapeColour;
import cz.ryvo.game.block.ShapeFactory;
import cz.ryvo.game.config.Config;
import cz.ryvo.game.level.Level;
import cz.ryvo.game.model.IntVector2;
import cz.ryvo.game.stage.PlayEvent;
import cz.ryvo.game.stage.PlayStage;

public class PlayScreen implements Screen, EventListener {

    private Config config;

    final G001 game;

    private PlayStage stage;

    private List<Shape> shapes;

    private Image image;

    private ActorGestureListener listener;

    private Level level;

    private PlayBoard playBoard;

    private ShapeRenderer shapeRenderer;

    private Vector2 shapeGripOffset;

    private Texture shapeTexture;

    private IntVector2 shapeGridPosition;



    public PlayScreen(G001 game) {
        this.game = game;
        this.config = Config.getInstance();

        int[][] shapeLayout = {
                {1,1,0,0,0},
                {1,0,0,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1}
        };

        FitViewport viewport = new FitViewport(Gdx.app.getGraphics().getWidth(), Gdx.graphics.getHeight(), game.camera);
        this.stage = new PlayStage(viewport);

        ShapeFactory factory = new ShapeFactory(game.config.getBlockSize(), game.assets, this);

        shapes = new ArrayList<Shape>();
        shapes.add(factory.createShape(shapeLayout, ShapeColour.RED));


        Gdx.input.setInputProcessor(stage);
        image = new Image(game.assets.get("sand.png", Texture.class));

        for (Shape shape : shapes) {
            stage.addActor(shape);
            shape.setPosition(32, 32);
            //shape.getColor().a = 0.5f;
        }

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(this.game.camera.combined);
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.camera.combined);
        level = new Level("levels/level-001.json");
        playBoard = new PlayBoard(game.config.getBlockSize(), level.getBoard());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.assets.get("sand.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(playBoard, 100, 300);

        // Render moving shape phantom
        if (shapeGridPosition != null) {
            Color colorBackup = game.batch.getColor();
            Color color = Color.WHITE;
            color.a = 0.5f;
            game.batch.setColor(color);
            game.batch.draw(shapeTexture,
                    shapeGridPosition.getX() * config.getBlockSize() + config.getBoardMargin(),
                    shapeGridPosition.getY() * config.getBlockSize() + config.getBoardMargin() + config.getAdvertisementAreaHeight()
            );
            game.batch.setColor(colorBackup);
        }

        game.batch.end();

        renderGrid();


        stage.draw();
    }

    private void renderGrid() {
        float x1 = config.getBoardMargin();
        float y1 = config.getBoardMargin() + config.getAdvertisementAreaHeight();
        float x2 = x1 + config.getBoardWidth();
        float y2 = y1 + config.getBoardHeight();

        Gdx.gl.glLineWidth(1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        for(int i = 0; i < config.getBoardHeightInBlocks() + 1; i++) {
            float y = y1 + i * config.getBlockSize();
            shapeRenderer.line(x1, y, x2, y);
        }
        for(int i = 0; i < config.getBoardWidthInBlocks() + 1; i++) {
            float x = x1 + i * config.getBlockSize();
            shapeRenderer.line(x, y1, x, y2);
        }
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
    }

    @Override
    public boolean handle(Event event) {
        if (event instanceof PlayEvent) {
            PlayEvent playEvent = (PlayEvent) event;
            switch (playEvent.getType()) {
                case touchDown:
                    if (playEvent.getTarget() instanceof Shape) {
                        Shape shape = (Shape) playEvent.getTarget();
                        shapeTexture = shape.getTexture();
                        shapeGripOffset = new Vector2(playEvent.getX(), playEvent.getY());
                        shapeGridPosition = getPositionInGrid(shape);
                    } else {
                        shapeTexture = null;
                        shapeGripOffset = null;
                        shapeGridPosition = null;
                    }
                    System.out.println("TOUCH DOWN: x=" + shapeGripOffset.x + "; y=" + shapeGripOffset.y);
                    break;
                case touchDragged:
                    if (playEvent.getTarget() instanceof Shape) {
                        Shape shape = (Shape) playEvent.getTarget();
                        moveShape((Shape) playEvent.getTarget(), playEvent.getDeltaX(), playEvent.getDeltaY());
                        shapeGridPosition = getPositionInGrid(shape);
                        System.out.println("Aligned position in grid: x=" + shapeGridPosition .getX() + "; y=" + shapeGridPosition .getY());
                    }
                    break;
                case touchUp:
                    System.out.println("TOUCH UP: x=" + playEvent.getX() + "; y=" + playEvent.getY());
                    if (playEvent.getTarget() instanceof Shape) {
                        Vector2 position = calculatePositionInPixelsFromGridPosition(shapeGridPosition);
                        Shape shape = (Shape) playEvent.getTarget();
                        shape.setPosition(position.x, position.y);
                    }
                    shapeTexture = null;
                    shapeGripOffset = null;
                    shapeGridPosition = null;
                    break;
                default:
                    System.out.println("UNKNOWN PLAY EVENT TYPE");

            }
        } else {
            System.out.println("UNKNOWN EVENT");
        }
        return false;
    }


    private void moveShape(Shape shape, float deltaX, float deltaY) {
        Stage stage = shape.getStage();

        float x = shape.getX();
        float y = shape.getY();
        float width = shape.getWidth();
        float height = shape.getHeight();

        if (x + deltaX < 0) deltaX = -x;
        if (x + width + deltaX > stage.getWidth()) deltaX = stage.getWidth() - (x + width);
        if (y + deltaY < 0) deltaY = -y;
        if (y + height + deltaY > stage.getHeight()) deltaY = stage.getHeight() - (y + height);

        shape.moveBy(deltaX, deltaY);
    }

    private IntVector2 getPositionInGrid(Shape shape) {
        int x = Math.round((shape.getX() - config.getBoardMargin()) / config.getBlockSize());
        int y = Math.round((shape.getY() - config.getBoardBottom()) / config.getBlockSize());

        if (y < 0) {
            y = 0;
        } else if (y + shape.getHeightInBlocks() > config.getBoardHeightInBlocks()) {
            y = config.getBoardHeightInBlocks() - shape.getHeightInBlocks();
        }

        if (x < 0) {
            x = 0;
        } else if (x + shape.getWidthInBlocks() > config.getBoardWidthInBlocks()) {
            x = config.getBoardWidthInBlocks() - shape.getWidthInBlocks();
        }

        return new IntVector2(x, y);
    }

    private Vector2 calculatePositionInPixelsFromGridPosition(IntVector2 gridPosition) {
        float x = gridPosition.getX() * config.getBlockSize() + config.getBoardMargin();
        float y = gridPosition.getY() * config.getBlockSize() + config.getBoardBottom();
        return new Vector2(x, y);
    }
}
