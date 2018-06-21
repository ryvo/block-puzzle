package cz.ryvo.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;

import cz.ryvo.game.block.ShapeColour;
import cz.ryvo.game.map2d.Map2D;

public class JsonLevelReader {

    public static Level readFromFile(String fileName) {
        JsonReader reader = new JsonReader();
        JsonValue jsonRoot = reader.parse(Gdx.files.internal(fileName));
        return new Level(
                readTargetArea(jsonRoot),
                readShapes(jsonRoot)
        );
    }

    private static LevelTargetArea readTargetArea(JsonValue jsonValue) {
        JsonValue jsonLayout = jsonValue.get("targetArea").get("layout");
        return new LevelTargetArea(readMap2D(jsonLayout));
    }

    private static List<LevelShape> readShapes(JsonValue json) {
        List<LevelShape> shapes = new ArrayList<LevelShape>();
        JsonValue.JsonIterator jsonIterator = json.get("shapes").iterator();
        while(jsonIterator.hasNext()) {
            JsonValue jsonShape = jsonIterator.next();
            shapes.add(new LevelShape(
                    readMap2D(jsonShape.get("layout")),
                    ShapeColour.valueOf(jsonShape.get("colour").asString())
            ));
        }
        return shapes;
    }

    private static Map2D readMap2D(JsonValue jsonValue) {
        return new Map2D(
            jsonValue.get("rowSize").asInt(),
            jsonValue.get("data").asIntArray()
        );
    }
}
