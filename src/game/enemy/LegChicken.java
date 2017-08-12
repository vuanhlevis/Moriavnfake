package game.enemy;

import game.base.FrameCounter;
import game.base.GameObject;
import game.base.Settings;
import game.base.Vector2D;
import game.base.animator.LegChickenAnimator;
import game.base.physics.BoxCollider;
import game.base.physics.PhysicsBody;
import game.player.Player;

import java.util.Random;

/**
 * Created by levua on 8/12/2017.
 */
public class LegChicken extends GameObject implements PhysicsBody {

    BoxCollider boxCollider;
    public Vector2D velocity;
    public FrameCounter frameCounter;
    public LegChickenAnimator enemyAnimator;
    Random random;
    public LegChicken() {
        super();
        this.frameCounter = new FrameCounter(150);
        this.boxCollider = new BoxCollider(29,29);
        enemyAnimator = new LegChickenAnimator();
        this.renderer = enemyAnimator;
        this.velocity = new Vector2D();
//        this.position.set(600,10);
        children.add(boxCollider);
        this.random = new Random();

        this.velocity.x = random.nextInt(3) - 3;
//        velocity.x = 0.5f;
    }


    @Override
    public void run(Vector2D parentPosition) {


        super.run(parentPosition);

        animate();


        if (this.position.y >= Settings.GAMEPLAY_HEIGHT) this.isActive = false;


        this.velocity.y +=  Player.instance.gravity;
        this.moveVertical(boxCollider,screenPosition,this.velocity);
        this.position.y += velocity.y;

        this.moveHorizontal(boxCollider,screenPosition,velocity);
        this.position.x += velocity.x;

        if (this.position.y <= 360) {
            this.position.y -= 3;

        }
        else this.velocity.x = 0;

//        if (frameCounter.run()) {
//            velocity.x *= -1;
//            frameCounter.reset();
//            frameCounter = new FrameCounter(5000);
//        }

//        System.out.println(Player.instance.alive);


    }

    private void animate() {
        enemyAnimator.run(this);
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
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
        return 69;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public Vector2D getStartPosition() {
        return null;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public void refresh() {
        super.refresh();

        this.isActive = true;
    }
}
