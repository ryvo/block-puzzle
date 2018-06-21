package cz.ryvo.game.level;

import cz.ryvo.game.map2d.Map2D;

public class LevelTargetArea {

    final Map2D layout;

    public LevelTargetArea(Map2D layout) {
        this.layout = layout;
    }

    public Map2D getLayout() {
        return layout;
    }
}
