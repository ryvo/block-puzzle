package cz.ryvo.game.level;

public class GridMap {
    private final int width;
    private final int height;
    private final int[] data;

    public GridMap(int width, int height, int[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getData() {
        return data;
    }
}
