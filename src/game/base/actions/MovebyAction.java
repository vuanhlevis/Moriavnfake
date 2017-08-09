package game.base.actions;

import game.base.FrameCounter;
import game.base.GameObject;
import game.base.Vector2D;

/**
 * Created by levua on 8/3/2017.
 */
public class MovebyAction implements Action {

    private Vector2D velocity;
    private FrameCounter frameCounter;

    public MovebyAction(Vector2D velocity, int countMax) {
        this.velocity = velocity;
        this.frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        if (frameCounter.run()) {
            return true;
        }

        gameObject.position.addUp(velocity);
        return false;
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
