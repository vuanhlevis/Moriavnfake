package game.map;

import game.base.Vector2D;
import game.base.physics.BoxCollider;

/**
 * Created by levua on 8/7/2017.
 */
public class Stone extends TileMember {
    BoxCollider boxCollider;
    public Stone(int index, int type, Vector2D position) {

        super(index, type, position);
        this.boxCollider = new BoxCollider(30,30);
        this.position = position;
        this.type = type;
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    @Override
    public int getType() {
        return this.type;
    }


    @Override
    public boolean isDisabled() {
        return this.dissable;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }
}
