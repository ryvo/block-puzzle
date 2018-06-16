package cz.ryvo.game.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import cz.ryvo.game.level.GridMap;

public class PlayBoard extends Texture {

    private final GridMap grid;

    public PlayBoard(int blockSize, GridMap grid) {
        super(createTexture(blockSize, grid));
        this.grid = grid;
    }

    public static Pixmap createTexture(int blockSize, GridMap grid) {
        Pixmap pixmap = new Pixmap(grid.getWidth() * blockSize, grid.getHeight() * blockSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();

        pixmap.setColor(0, 0, 0, .3f);
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                int cell = grid.getData()[y * grid.getHeight() + x];
                if (cell > 0) {
                    pixmap.fillRectangle(x * blockSize, y * blockSize, blockSize, blockSize);
                }
            }
        }
        return pixmap;
    }
}
