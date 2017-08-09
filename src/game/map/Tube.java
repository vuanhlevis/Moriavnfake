package game.map;

import game.base.Vector2D;
import game.base.physics.BoxCollider;
import game.player.Player;

import java.util.Vector;

/**
 * Created by Admin on 8/9/2017.
 */
public class Tube extends TileMember {
    BoxCollider boxCollider;
    public static Tube instance;
    Vector2D velocity;

    public static Vector<Tube> tubes = new Vector<>();
    public Tube(int index, int type, Vector2D position) {
        super(index,type,position);
        boxCollider = new BoxCollider(30,30);
        this.velocity = new Vector2D();
        this.position = position;
        this.type = type;
        children.add(boxCollider);
        instance = this;
        tubes.add(this);
    }

    public boolean canGoDown(){
        if (Player.instance.position.x >= instance.position.x - 15 && Player.instance.position.x >= instance.position.x - 15    ){
            return true;
        }
        else return false;
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
//        System.out.println(this.boxCollider);
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
    public boolean isDisabled() {
        return this.dissable;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

}
