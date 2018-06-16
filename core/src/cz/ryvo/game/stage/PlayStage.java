package cz.ryvo.game.stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayStage extends Stage {

    public PlayStage() {
        init();
    }

    public PlayStage(Viewport viewport) {
        super(viewport);
        init();
    }

    public PlayStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        init();
    }

    private void init() {
    }


}
