package cz.ryvo.game.block;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import java.util.Set;

public class ShapeFactory {

    private AssetManager assets;
    private EventListener listener;
    private final int blockSize;

    public ShapeFactory(int blockSize, AssetManager assets, EventListener listener) {
        this.assets = assets;
        this.listener = listener;
        this.blockSize = blockSize;
    }

    public Shape createShape(Set<BlockPosition> blockPositions, ShapeColour colour) {
        BlockPosition shapeBounds = getShapeBounds(blockPositions);
        Pixmap texturePixmap = newPixmap(shapeBounds.getX(), shapeBounds.getY());
        Pixmap maskPixmap = newPixmap(shapeBounds.getX(), shapeBounds.getY());
        maskPixmap.setColor(Shape.MASK_COLOR);

        String colourName = colour.name().toLowerCase();
        Texture blockTexture = assets.get("block-" + colourName + ".png", Texture.class);
        TextureData textureData = blockTexture.getTextureData();
        textureData.prepare();
        Pixmap blockPixmap = textureData.consumePixmap();

        for (BlockPosition position : blockPositions) {
            texturePixmap.drawPixmap(blockPixmap,0, 0, blockPixmap.getWidth(), blockPixmap.getHeight(), position.getX() * blockSize, shapeBounds.getY() - (position.getY() + 1) * blockSize, blockSize, blockSize);
            maskPixmap.fillRectangle(position.getX() * blockSize, position.getY() * blockSize, blockSize, blockSize);
        }

        Texture shapeTexture = new Texture(texturePixmap);

        textureData.disposePixmap();
        blockPixmap.dispose();
        texturePixmap.dispose();
        //maskPixmap.dispose();

        return new Shape(shapeTexture, maskPixmap, listener);
    }

    private BlockPosition getShapeBounds(Set<BlockPosition> blockPositions) {
        int maxX = 0;
        int maxY = 0;
        for (BlockPosition point : blockPositions) {
            if (point.getX() > maxX) maxX = point.getX();
            if (point.getY() > maxY) maxY = point.getY();
        }
        return new BlockPosition((maxX + 1) * blockSize, (maxY + 1) * blockSize);
    }

    private Pixmap newPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
        return pixmap;
    }
}
