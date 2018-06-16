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

    private final int heightInBlocks;
    private final int widthInBlocks;
    private final Texture texture;
    private final Pixmap mask;

    public Shape(int widthInBlocks, int heightInBlocks, Texture texture, Pixmap mask, EventListener listener) {
        super(texture);
        this.widthInBlocks = widthInBlocks;
        this.heightInBlocks = heightInBlocks;
        this.texture = texture;
        this.mask = mask;
        this.addListener(new ShapeGestureListener(listener));
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        if (MASK_COLOR.toIntBits() == mask.getPixel(Math.round(x), Math.round(y))) {
            return this;
        }
        return null;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHeightInBlocks() {
        return heightInBlocks;
    }

    public int getWidthInBlocks() {
        return widthInBlocks;
    }
}
