package cz.ryvo.game.stage;

import com.badlogic.gdx.scenes.scene2d.Event;

public class PlayEvent extends Event {

    private Type type;
    private float x;
    private float y;
    private float deltaX;
    private float deltaY;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public enum Type {
        /** A new touch for a pointer on the stage was detected */
        touchDown,
        /** A pointer has stopped touching the stage. */
        touchUp,
        /** A pointer that is touching the stage has moved. */
        touchDragged,
    }
}
