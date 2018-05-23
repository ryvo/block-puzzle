package cz.ryvo.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Level {

    private GridMap board;

    public Level(String levelName) {
        load(levelName);
    }

    private void load(String levelName) {
        JsonReader reader = new JsonReader();
        JsonValue jsonRoot = reader.parse(Gdx.files.internal(levelName));

        JsonValue jsonBoard = jsonRoot.get("board");
        board = new GridMap(
                jsonBoard.get("width").asInt(),
                jsonBoard.get("height").asInt(),
                jsonBoard.get("data").asIntArray()
        );
    }

    public GridMap getBoard() {
        return board;
    }
}
