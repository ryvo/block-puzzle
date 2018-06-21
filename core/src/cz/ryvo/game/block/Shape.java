package cz.ryvo.game.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import cz.ryvo.game.level.LevelShape;

public class Shape extends Image {

    public static final Color MASK_COLOR = Color.WHITE;

    private final LevelShape levelShape;
    private final Texture texture;
    private final Pixmap mask;

    public Shape(LevelShape levelShape, Texture texture, Pixmap mask) {
        super(texture);
        this.levelShape = levelShape;
        this.texture = texture;
        this.mask = mask;
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        int pixel = mask.getPixel(Math.round(x), mask.getHeight() - 1 - Math.round(y));
        int maskc = MASK_COLOR.toIntBits();
        System.out.println("x: " + Math.round(x) + "; y: " + (mask.getHeight() - 1 - Math.round(y)) + "; pixel: " + pixel + "; mask: " + Color.WHITE);
        if (maskc == pixel) {
            System.out.println("Shape '" + levelShape.getColour().name() + "' hit.");
            return this;
        }
        return null;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHeightInBlocks() {
        return levelShape.getLayout().getHeight();
    }

    public int getWidthInBlocks() {
        return levelShape.getLayout().getWidth();
    }
}
