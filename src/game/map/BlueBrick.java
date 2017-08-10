package game.map;

import game.base.Settings;
import game.base.Vector2D;
import game.base.physics.BoxCollider;
import game.base.physics.PhysicsBody;
import game.player.Player;

/**
 * Created by levua on 8/7/2017.
 */
public class BlueBrick extends TileMember implements PhysicsBody {
    public static BlueBrick instance = new BlueBrick(0,-1,new Vector2D());
    public boolean status;
    public Vector2D velocity;
    BoxCollider boxCollider;
    Vector2D savePosition;

    public BlueBrick(int index, int type, Vector2D position) {
        super(index, type, position);
        instance = this;
        this.status = false;
        this.velocity = new Vector2D();
        this.savePosition = position;
        this.boxCollider = new BoxCollider(30,30);
        this.position = position;
        this.type = type;
        children.add(boxCollider);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (this.position.y >= Settings.GAMEPLAY_HEIGHT) this.isActive = false;

        if (status) {
            this.position.addUp(0,10);
        }

        if (Player.instance.position.y >= Settings.GAMEPLAY_HEIGHT && !Player.instance.alive) {
            refresh();
        }

//        System.out.println(this.position + "hihi");
//        System.out.println(Player.instance.getBoxCollider());
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
        this.status = false;
        this.velocity = new Vector2D();
    }
}
