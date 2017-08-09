package game.enemy;

import game.Utils;
import game.base.FrameCounter;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.physics.BoxCollider;
import game.base.physics.PhysicsBody;
import game.base.renderer.ImageRenderer;
import game.player.Player;

import static game.map.TileMember.TYPE_ENEMY;

/**
 * Created by levua on 8/7/2017.
 */
public class Enemy extends GameObject implements PhysicsBody {
    BoxCollider boxCollider;
    Vector2D velocity;
    FrameCounter frameCounter;
    public Enemy() {
        super();
        this.frameCounter = new FrameCounter(150);
        this.boxCollider = new BoxCollider(29,29);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("yellow_square.jpg"));
        this.velocity = new Vector2D();
        children.add(boxCollider);
        velocity.x = -0.5f;
    }


    @Override
    public void run(Vector2D parentPosition) {

        super.run(parentPosition);

        this.velocity.y +=  Player.instance.gravity;
        this.moveVertical(boxCollider,screenPosition,this.velocity);
        this.position.y += velocity.y;

        this.moveHorizontal(boxCollider,screenPosition,velocity);
        this.position.x += velocity.x;

        if (frameCounter.run()) {
            velocity.x *= -1;
            frameCounter.reset();
            frameCounter = new FrameCounter(200);
        }
        

    }


    @Override
    public boolean isDisabled() {
        return false;
    }

    @Override
    public void setDissabled(boolean disable) {

    }

    @Override
    public int getType() {
        return TYPE_ENEMY;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

}
