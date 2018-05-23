package cz.ryvo.game.stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import cz.ryvo.game.block.Shape;

public class PlayStage extends Stage implements EventListener {

    public PlayStage() {
    }

    public PlayStage(Viewport viewport) {
        super(viewport);
    }

    public PlayStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
    }

    @Override
    public boolean handle(Event event) {
        if (event instanceof PlayEvent) {
            PlayEvent playEvent = (PlayEvent) event;
            switch (playEvent.getType()) {
                case touchDown:
                    System.out.println("TOUCH DOWN: x=" + playEvent.getX() + "; y=" + playEvent.getY());
                    break;
                case touchDragged:
                    System.out.println("TOUCH DRAG: x=" + playEvent.getX() + "; y=" + playEvent.getY() + "; deltaX=" + playEvent.getDeltaX() + "; deltaY=" + playEvent.getDeltaX());
                    if (playEvent.getTarget() instanceof Shape) {
                        moveShape((Shape) playEvent.getTarget(), playEvent.getDeltaX(), playEvent.getDeltaY());
                    }
                    break;
                case touchUp:
                    System.out.println("TOUCH UP: x=" + playEvent.getX() + "; y=" + playEvent.getY());
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

}
