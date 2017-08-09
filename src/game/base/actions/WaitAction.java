package game.base.actions;

import game.base.FrameCounter;
import game.base.GameObject;


/**
 * Created by huynq on 8/1/17.
 */
public class WaitAction implements Action {

    private FrameCounter frameCounter;

    public WaitAction(int countMax) {
        frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        return frameCounter.run();
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
