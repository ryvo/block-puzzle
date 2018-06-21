package cz.ryvo.game.level;

import cz.ryvo.game.block.ShapeColour;
import cz.ryvo.game.map2d.Map2D;

public class LevelShape {

    final Map2D layout;
    final ShapeColour colour;

    public LevelShape(Map2D layout, ShapeColour colour) {
        this.layout = layout;
        this.colour = colour;
    }

    public Map2D getLayout() {
        return layout;
    }

    public ShapeColour getColour() {
        return colour;
    }
}
