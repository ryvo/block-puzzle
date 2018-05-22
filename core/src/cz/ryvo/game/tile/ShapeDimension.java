package cz.ryvo.game.tile;

public class ShapeDimension {

    private final int width;
    private final int height;

    public ShapeDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShapeDimension that = (ShapeDimension) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return 31 * width + height;
    }

    @Override
    public String toString() {
        return "ShapeDimension{width=" + width +", height=" + height + "}";
    }
}
