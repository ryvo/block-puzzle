package cz.ryvo.game.map2d;

public class Map2DOperations {

    private void setValue(Map2D dst, int x, int y, int value) {
        if (x < 0 || x > (dst.width - 1) || y < 0 || y > (dst.height - 1)) {
            return;
        }
        dst.data[y][x] = value;
    }

    public boolean canPutShapeIntoMatrix(Map2D dst, int srcX, int srcY, Map2D src) {
        for (int y = 0; y < src.height; y++) {
            for (int x = 0; x < src.width; x++) {
                if (dst.isSet(srcX + x, srcY + y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void putShapeIntoMatrix(Map2D dst, int srcX, int srcY, Map2D src) {
        for (int y = 0; y < src.height; y++) {
            for (int x = 0; x < src.width; x++) {
                if (src.isSet(x, y)) {
                    setValue(dst, srcX + x, srcY + y, 1);
                }
            }
        }
    }

    public void removeShapeFromMatrix(Map2D dst, int srcX, int srcY, Map2D src) {
        for (int y = 0; y < src.height; y++) {
            for (int x = 0; x < src.width; x++) {
                if (src.isSet(x, y)) {
                    setValue(dst, srcX + x, srcY + y, 1);
                }
            }
        }
    }

    public boolean isComplete(Map2D matrix) {
        for (int y = 0; y < matrix.height; y++) {
            for (int x = 0; x < matrix.width; x++) {
                if (!matrix.isSet(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

}
