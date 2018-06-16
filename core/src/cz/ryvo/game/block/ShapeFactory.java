package cz.ryvo.game.block;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import cz.ryvo.game.model.IntVector2;

public class ShapeFactory {

    private AssetManager assets;
    private EventListener listener;
    private final int blockSize;

    public ShapeFactory(int blockSize, AssetManager assets, EventListener listener) {
        this.assets = assets;
        this.listener = listener;
        this.blockSize = blockSize;
    }

    public Shape createShape(int[][] shapeLayout, ShapeColour colour) {
        IntVector2 dimensionInBlocks = getShapeDimensionInBlocks(shapeLayout);
        IntVector2 dimension = new IntVector2(dimensionInBlocks.getX() * blockSize, dimensionInBlocks.getY() * blockSize);

        Pixmap texturePixmap = newPixmap(dimension.getX(), dimension.getY());

        Pixmap maskPixmap = newPixmap(dimension.getX(), dimension.getY());
        maskPixmap.setColor(Shape.MASK_COLOR);

        Pixmap blockPixmap = getBlockPixmap(colour);

        for (int row = 0; row < shapeLayout.length; row++) {
            for (int col = 0; col < shapeLayout[row].length; col++) {
                if (shapeLayout[row][col] == 1) {
                    int x = col * blockSize;
                    int y = row * blockSize;
                    texturePixmap.drawPixmap(blockPixmap, 0, 0, blockPixmap.getWidth(), blockPixmap.getHeight(), x, y, blockSize, blockSize);
                    maskPixmap.fillRectangle(x, y, blockSize, blockSize);
                }
            }
        }

        Texture shapeTexture = new Texture(texturePixmap);

        blockPixmap.dispose();
        texturePixmap.dispose();

        return new Shape(dimensionInBlocks.getX(), dimensionInBlocks.getY(), shapeTexture, maskPixmap, listener);
    }

    private IntVector2 getShapeDimensionInBlocks(int[][] shapeLayout) {
        int maxHeight = shapeLayout.length;
        int maxWidth = 0;
        for (int y = 0; y < shapeLayout.length; y++) {
            maxWidth = Math.max(maxWidth, shapeLayout[y].length);
        }
        return new IntVector2(maxWidth, maxHeight);
    }

    private Pixmap getBlockPixmap(ShapeColour colour) {
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
