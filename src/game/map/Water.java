package game.map;

import game.base.Settings;
import game.base.Vector2D;
import game.base.physics.BoxCollider;
import game.player.Player;

/**
 * Created by levua on 8/7/2017.
 */
public class Water extends TileMember {
    BoxCollider boxCollider;
    Vector2D savePosition;

    public Water(int index, int type, Vector2D position) {
        super(index, type, position);
        this.boxCollider = new BoxCollider(30, 30);
        this.position = position;
        this.savePosition = position;
        this.type = type;
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (this.position.y >= Settings.GAMEPLAY_HEIGHT) this.isActive = false;

        if (Player.instance.position.y >= Settings.GAMEPLAY_HEIGHT - 50 && !Player.instance.alive) {
            refresh();
        }
    }

    @Override
    public Vector2D getStartPosition() {
        return savePosition;
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

    @Override
    public void refresh() {
        super.refresh();
    }
}
