package cz.ryvo.game.block;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import cz.ryvo.game.level.LevelShape;

public class ShapeFactory {

    private final int blockSize;
    private final AssetManager assets;

    public ShapeFactory(int blockSize, AssetManager assets) {
        this.assets = assets;
        this.blockSize = blockSize;
    }

    public Shape createShape(LevelShape levelShape) {
        int widthInBlocks = levelShape.getLayout().getWidth();
        int heightInBlocks = levelShape.getLayout().getHeight();

        int width = widthInBlocks * blockSize;
        int height = heightInBlocks * blockSize;

        Pixmap texturePixmap = newPixmap(width, height);
        texturePixmap.setColor(Shape.MASK_COLOR);

        Pixmap maskPixmap = newPixmap(width, height);
        maskPixmap.setColor(Shape.MASK_COLOR);

        Pixmap blockPixmap = newBlockPixmap(assets, levelShape.getColour());

        for (int yb = 0; yb < heightInBlocks; yb++) {
            for (int xb = 0; xb < widthInBlocks; xb++) {
                if (levelShape.getLayout().isSet(xb, yb)) {
                    int x = xb * blockSize;
                    int y = yb * blockSize;
                    texturePixmap.drawPixmap(blockPixmap, 0, 0, blockPixmap.getWidth(), blockPixmap.getHeight(), x, y, blockSize, blockSize);
                    maskPixmap.fillRectangle(x, y, blockSize, blockSize);
                }
            }
        }

        Texture shapeTexture = new Texture(texturePixmap);

        blockPixmap.dispose();
        texturePixmap.dispose();

        return new Shape(levelShape, shapeTexture, maskPixmap);
    }

    private Pixmap newBlockPixmap(AssetManager assets, ShapeColour colour) {
        String colourName = colour.name().toLowerCase();
        Texture blockTexture = assets.get("block-" + colourName + ".png", Texture.class);
        TextureData textureData = blockTexture.getTextureData();
        textureData.prepare();
        Pixmap result = textureData.consumePixmap();
        textureData.disposePixmap();
        return result;
    }

    private Pixmap newPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
        return pixmap;
    }
}
