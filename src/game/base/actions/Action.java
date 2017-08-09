package game.base.actions;

import game.base.GameObject;

/**
 * Created by levua on 8/3/2017.
 */
public interface Action {
    boolean run(GameObject gameObject);
    void reset();
}
