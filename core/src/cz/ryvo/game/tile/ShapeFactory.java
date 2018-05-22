package cz.ryvo.game.tile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import java.util.Set;

import static cz.ryvo.game.config.Config.BLOCK_SIZE;

public class ShapeFactory {

    private AssetManager assets;

    public ShapeFactory(AssetManager assets) {
        this.assets = assets;
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
            texturePixmap.drawPixmap(blockPixmap,0, 0, blockPixmap.getWidth(), blockPixmap.getHeight(), position.getX() * BLOCK_SIZE, shapeBounds.getY() - (position.getY() + 1) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            maskPixmap.fillRectangle(position.getX() * BLOCK_SIZE, position.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }

        Texture shapeTexture = new Texture(texturePixmap);

        textureData.disposePixmap();
        blockPixmap.dispose();
        texturePixmap.dispose();
        //maskPixmap.dispose();

        return new Shape(shapeTexture, maskPixmap);
    }

    private BlockPosition getShapeBounds(Set<BlockPosition> blockPositions) {
        int maxX = 0;
        int maxY = 0;
        for (BlockPosition point : blockPositions) {
            if (point.getX() > maxX) maxX = point.getX();
            if (point.getY() > maxY) maxY = point.getY();
        }
        return new BlockPosition((maxX + 1) * BLOCK_SIZE, (maxY + 1) * BLOCK_SIZE);
    }

    private Pixmap newPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
        return pixmap;
    }
}
