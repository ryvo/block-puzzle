package cz.ryvo.game.level;

import java.util.List;

public class Level {

    LevelTargetArea targetArea;
    List<LevelShape> shapes;

    public Level(LevelTargetArea targetArea, List<LevelShape> shapes) {
        this.targetArea = targetArea;
        this.shapes = shapes;
    }

    public LevelTargetArea getTargetArea() {
        return targetArea;
    }

    public List<LevelShape> getShapes() {
        return shapes;
    }
}
