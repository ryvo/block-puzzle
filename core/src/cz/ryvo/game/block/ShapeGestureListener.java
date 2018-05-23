package cz.ryvo.game.block;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Pools;

import cz.ryvo.game.stage.PlayEvent;

public class ShapeGestureListener extends ActorGestureListener {

    public ShapeGestureListener() {
    }

    @Override
    public void touchDown (InputEvent event, float x, float y, int pointer, int button) {
        Shape shape = (Shape) event.getTarget();

        if (shape.getStage() instanceof EventListener) {
            PlayEvent playStageEvent = createEvent(shape);
            playStageEvent.setType(PlayEvent.Type.touchDown);
            playStageEvent.setX(x);
            playStageEvent.setY(y);

            ((EventListener) shape.getStage()).handle(playStageEvent);

            Pools.free(playStageEvent);
        }
    }

    @Override
    public void pan (InputEvent event, float x, float y, float deltaX, float deltaY) {
        Shape shape = (Shape) event.getTarget();

        if (shape.getStage() instanceof EventListener) {
            PlayEvent playStageEvent = createEvent(shape);
            playStageEvent.setType(PlayEvent.Type.touchDragged);
            playStageEvent.setX(x);
            playStageEvent.setY(y);
            playStageEvent.setDeltaX(deltaX);
            playStageEvent.setDeltaY(deltaY);

            ((EventListener) shape.getStage()).handle(playStageEvent);

            Pools.free(playStageEvent);
        }
    }

    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        Shape shape = (Shape) event.getTarget();
        if (shape.getStage() instanceof EventListener) {
            PlayEvent playStageEvent = createEvent(shape);
            playStageEvent.setType(PlayEvent.Type.touchUp);
            playStageEvent.setX(x);
            playStageEvent.setY(y);

            ((EventListener) shape.getStage()).handle(playStageEvent);

            Pools.free(playStageEvent);
        }
    }

    private PlayEvent createEvent(Shape shape) {
        PlayEvent playStageEvent = Pools.obtain(PlayEvent.class);
        playStageEvent.setStage(shape.getStage());
        playStageEvent.setTarget(shape);
        playStageEvent.setListenerActor(shape);
        return playStageEvent;
    }
}
