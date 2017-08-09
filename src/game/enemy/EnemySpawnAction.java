package game.enemy;

import game.base.GameObject;
import game.base.Vector2D;
import game.base.actions.Action;

/**
 * Created by levua on 8/8/2017.
 */
public class EnemySpawnAction implements Action {

    private Vector2D position;
    public EnemySpawnAction(Vector2D position) {
        this.position = position;
    }

    @Override
    public boolean run(GameObject gameObject) {


        return true;
    }

    @Override
    public void reset() {

    }
}
