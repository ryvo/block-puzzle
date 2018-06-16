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
    private final int boardWidthInBlocks;
    private final int boardHeightInBlocks;
    private final int boardWidth;
    private final int boardHeight;
    private final int boardMargin;

    private Config() {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        int preliminaryPadding = (int)(screenWidth * (PLAY_BOARD_PADDING_PERCENTAGE / 100f) / 2);

        int playAreaWidth = screenWidth - 2 * preliminaryPadding;
        boardWidthInBlocks = PLAY_BOARD_BLOCKS_PER_ROW;
        blockSize = playAreaWidth / boardWidthInBlocks;
        boardWidth = blockSize * boardWidthInBlocks;
        boardMargin = (screenWidth - boardWidth) / 2;

        int playAreaHeight = screenHeight - PLAY_TOOLBAR_HEIGHT - 2 * boardMargin - ADVERTISEMENT_AREA_HEIGHT;
        boardHeightInBlocks = playAreaHeight / blockSize;
        boardHeight = boardHeightInBlocks * blockSize;

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

    public int getBoardWidthInBlocks() {
        return boardWidthInBlocks;
    }

    public int getBoardHeightInBlocks() {
        return boardHeightInBlocks;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardMargin() {
        return boardMargin;
    }

    public int getAdvertisementAreaHeight() {
        return ADVERTISEMENT_AREA_HEIGHT;
    }

    public int getBoardBottom() {
        return ADVERTISEMENT_AREA_HEIGHT + boardMargin;
    }

    @Override
    public String toString() {
        return "Config{" +
                "blockSize=" + blockSize +
                ", playBoardBlocksPerRow=" + boardWidthInBlocks +
                ", playBoardBlocksPerColumn=" + boardHeightInBlocks +
                ", gridOfBlocksWidth=" + boardWidth +
                ", gridOfBlocksHeight=" + boardHeight +
                ", playBoardPadding=" + boardMargin +
                '}';
    }
}
