package cz.ryvo.game.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Shape extends Image {

    public static final Color MASK_COLOR = Color.WHITE;

    private final Pixmap mask;
    private final int maskColor;

    public Shape(Texture texture, Pixmap mask) {
        super(texture);
        this.mask = mask;
        maskColor = MASK_COLOR.toIntBits();
        addGestureListener();
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        int a = mask.getPixel(Math.round(x), Math.round(y));
        if (maskColor == a) {
            System.out.println("HIT: true");
            return this;
        }
        System.out.println("HIT: false");
        return null;
    }

    private void addGestureListener() {
        this.addListener(new ActorGestureListener() {
            @Override
            public void touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TOUCH DOWN: x=" + x + "; y=" + y);
            }

            @Override
            public void pan (InputEvent event, float x, float y, float deltaX, float deltaY) {
                Shape shape = (Shape) event.getTarget();
                shape.moveBy(deltaX, deltaY);
                System.out.println("PAN: x=" + x + "; y=" + y);
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TOUCH UP: x=" + x + "; y=" + y);
            }
        });
    }
}
