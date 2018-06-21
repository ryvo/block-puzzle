package cz.ryvo.game.map2d;

public class Map2D {
    final int width;
    final int height;
    final int[][] data;

    public Map2D(int rowSize, int[] data) {
        if (data.length % rowSize > 0) {
            throw new IllegalArgumentException("Size of array 'data' must be whole number multiple of 'rowSize'.");
        }
        this.width = rowSize;
        this.height = data.length / rowSize;
        this.data = new int[height][width];
        mergeData(rowSize, data);
    }

    public Map2D(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new int[height][width];
    }

    private void mergeData(int rowSize, int[] data) {
        int x = 0;
        int y = 0;
        for(int i = 0; i < data.length; i++) {
            this.data[y][x] = data[i];
            if (x == rowSize - 1) {
                x = 0;
                y++;
            } else {
                x++;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isSet(int x, int y) {
        return data[y][x] != 0;
    }
}
