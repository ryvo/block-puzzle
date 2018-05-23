package cz.ryvo.game.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Shape extends Image {

    public static final Color MASK_COLOR = Color.WHITE;

    private final Texture texture;
    private final Pixmap mask;
    private final int maskColor;
    private Vector2 lastPosition;
    private boolean isBeeingPanned = false;
    private EventListener listener;

    public Shape(Texture texture, Pixmap mask, EventListener listener) {
        super(texture);
        this.texture = texture;
        this.mask = mask;
        this.listener = listener;
        maskColor = MASK_COLOR.toIntBits();
        this.addListener(new ShapeGestureListener());
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        if (maskColor == mask.getPixel(Math.round(x), Math.round(y))) {
            return this;
        }
        return null;
    }
}
