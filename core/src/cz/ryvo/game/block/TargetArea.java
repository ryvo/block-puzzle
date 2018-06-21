package cz.ryvo.game.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import cz.ryvo.game.level.LevelTargetArea;
import cz.ryvo.game.map2d.Map2D;

public class TargetArea extends Texture {

    private final LevelTargetArea levelTargetArea;

    public TargetArea(int blockSize, LevelTargetArea levelTargetArea) {
        super(createTexture(blockSize, levelTargetArea.getLayout()));
        this.levelTargetArea = levelTargetArea;
    }

    public static Pixmap createTexture(int blockSize, Map2D layout) {
        Pixmap pixmap = new Pixmap(layout.getWidth() * blockSize, layout.getHeight() * blockSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();

        pixmap.setColor(0, 0, 0, .3f);
        for (int y = 0; y < layout.getHeight(); y++) {
            for (int x = 0; x < layout.getWidth(); x++) {
                if (layout.isSet(x, y)) {
                    pixmap.fillRectangle(x * blockSize, y * blockSize, blockSize, blockSize);
                }
            }
        }
        return pixmap;
    }
}
