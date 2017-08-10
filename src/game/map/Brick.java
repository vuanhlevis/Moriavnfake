package game.map;

import game.base.Settings;
import game.base.Vector2D;
import game.base.physics.BoxCollider;
import game.base.physics.PhysicsBody;
import game.player.Player;

/**
 * Created by levua on 8/7/2017.
 */
public class Brick extends TileMember implements PhysicsBody {
    BoxCollider boxCollider;
    Vector2D velocity;
    Vector2D savePosition;

    public Brick(int index, int type, Vector2D position) {
        super(index, type, position);
        this.velocity = new Vector2D();
        this.savePosition = position;
        this.boxCollider = new BoxCollider(30, 30);
        this.position = position;
        this.type = type;
        children.add(boxCollider);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
        if (!Player.instance.alive) {
            refresh();
        }
        if (this.position.y >= Settings.GAMEPLAY_HEIGHT) this.isActive = false;


    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public Vector2D getStartPosition() {
        return savePosition;
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
        this.velocity = new Vector2D();

        this.setActive(true);
        this.position = savePosition;
    }
}
