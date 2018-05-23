package cz.ryvo.game.config;

import com.badlogic.gdx.Gdx;

public class Config {

    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 800;

    public static final int PLAY_TOOLBAR_HEIGHT = 50;
    public static final int PLAY_BOARD_BLOCKS_PER_ROW = 11;
    public static final int PLAY_BOARD_PADDING_PERCENTAGE = 5;
    public static final int ADVERTISEMENT_AREA_HEIGHT = 50;

    public static final String I18_GAME_NAME = "game.name";
    public static final String I18_BUTTON_START_LABEL = "button.start.label";

    private static Config config;

    private final int blockSize;
    private final int playBoardBlocksPerRow;
    private final int playBoardBlocksPerColumn;
    private final int gridOfBlocksWidth;
    private final int gridOfBlocksHeight;
    private final int playBoardPadding;

    private Config() {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        int preliminaryPadding = (int)(screenWidth * (PLAY_BOARD_PADDING_PERCENTAGE / 100f) / 2);

        int playAreaWidth = screenWidth - 2 * preliminaryPadding;
        playBoardBlocksPerRow = PLAY_BOARD_BLOCKS_PER_ROW;
        blockSize = playAreaWidth / playBoardBlocksPerRow;
        gridOfBlocksWidth = blockSize * playBoardBlocksPerRow;
        playBoardPadding = (screenWidth - gridOfBlocksWidth) / 2;

        int playAreaHeight = screenHeight - PLAY_TOOLBAR_HEIGHT - 2 * playBoardPadding - ADVERTISEMENT_AREA_HEIGHT;
        playBoardBlocksPerColumn = playAreaHeight / blockSize;
        gridOfBlocksHeight = playBoardBlocksPerColumn * blockSize;

        System.out.println(toString());
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getPlayBoardBlocksPerRow() {
        return playBoardBlocksPerRow;
    }

    public int getPlayBoardBlocksPerColumn() {
        return playBoardBlocksPerColumn;
    }

    public int getGridOfBlocksWidth() {
        return gridOfBlocksWidth;
    }

    public int getGridOfBlocksHeight() {
        return gridOfBlocksHeight;
    }

    public int getPlayBoardPadding() {
        return playBoardPadding;
    }

    @Override
    public String toString() {
        return "Config{" +
                "blockSize=" + blockSize +
                ", playBoardBlocksPerRow=" + playBoardBlocksPerRow +
                ", playBoardBlocksPerColumn=" + playBoardBlocksPerColumn +
                ", gridOfBlocksWidth=" + gridOfBlocksWidth +
                ", gridOfBlocksHeight=" + gridOfBlocksHeight +
                ", playBoardPadding=" + playBoardPadding +
                '}';
    }
}
