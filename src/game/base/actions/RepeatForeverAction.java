package game.base.actions;

import game.base.GameObject;

/**
 * Created by huynq on 8/1/17.
 */
public class RepeatForeverAction implements Action {
    private Action action;

    public RepeatForeverAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean run(GameObject gameObject) {
        if (action.run(gameObject)) {
            action.reset();
        }
        return false;
    }

    @Override
    public void reset() {
        action.reset();
    }
}
