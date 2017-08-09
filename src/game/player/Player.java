package game.player;

import game.Utils;
import game.base.*;
import game.base.actions.WaitAction;
import game.base.inputs.InputManager;
import game.base.physics.BoxCollider;
import game.base.physics.Physics;
import game.base.physics.PhysicsBody;
import game.base.renderer.ImageRenderer;
import game.enemy.Enemy;
import game.map.*;

import static game.map.TileMember.*;

/**
 * Created by levua on 8/3/2017.
 */
public class Player extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    Contraints contraints;
    public boolean alive;
    Class standclass[] = {Brick.class, Stone.class, InfinityStone.class, Water.class, Enemy.class};
    WaitAction waitAction;

    public float gravity = 1f;
    public Vector2D velocity;
    public static Player instance = new Player();

//    private float skinWidth = 4;
//    private float skinHeight = 4;

    public Player() {
        super();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("chicken.png"));
        this.boxCollider = new BoxCollider(29, 29);
        this.children.add(boxCollider);
        this.alive = true;
        this.waitAction = new WaitAction(50);
        contraints = new Contraints(0, Settings.GAMEPLAY_HEIGHT, 0, Settings.MAP_WIDTH);
        instance = this;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        this.velocity.y += gravity;

        this.velocity.x = 0;

        if (InputManager.instance.leftPressed && alive) {
            this.velocity.x -= 5;
        }

        if (InputManager.instance.rightPressed && alive) {
            this.velocity.x += 5;
        }

        if (InputManager.instance.upPressed && alive) {
            //Brick.class
            if (Physics.bodyInRect(position.add(0, 1), boxCollider.width, boxCollider.height, standclass) != null)
                this.velocity.y = -15;
        }

        moveHorizontal();
        this.position.x += velocity.x;

        moveVertical();

        hitEnemy();

        hitStone();

        this.position.y += velocity.y;
        moveSpecial();


        if (!this.alive) {
            waitAction.run(this);
        }
        this.contraints.make(this.position);
        waitAction.reset();


    }

    private void hitEnemy() {

    }


    private void hitStone() {
        float deltaX = velocity.x > 0 ? 1 : -1;
        PhysicsBody body = Physics.bodyInRect(position.add(velocity.x, 0), boxCollider.width, boxCollider.height, TileMember.class);
        if (body != null && body.getType() == TYPE_STONE && alive) {
            while (Physics.bodyInRect(position.add(deltaX, 0), boxCollider.width, boxCollider.height, TileMember.class) == null) {
                position.addUp(deltaX, 0);
            }
            this.velocity.x = 0;
        }


        float detalY = velocity.y > 0 ? 1 : -1;
        PhysicsBody boody = Physics.bodyInRect(position.add(0, velocity.y), boxCollider.width, boxCollider.height, Stone.class);
        if (boody != null && body.getType() == TYPE_STONE && alive) {
            while (Physics.bodyInRect(position.add(0, detalY), boxCollider.width, boxCollider.height, Stone.class) == null) {
                position.addUp(0, detalY);
            }
            this.velocity.y = 0;

        }
    }

    private void moveHorizontal() {
        PhysicsBody body = Physics.bodyInRect(position.add(velocity.x, 0), boxCollider.width, boxCollider.height, standclass);
        if (body != null && alive) {
            float detalY = Mathx.sign(velocity.y);
            float deltaX = Mathx.sign(velocity.x);
            while (Physics.bodyInRect(position.add(deltaX, 0), boxCollider.width, boxCollider.height, standclass) == null) {
                position.addUp(deltaX, 0);
            }
            this.velocity.x = 0;
            if (body.getType() == TYPE_WATER || body.getType() == TYPE_ENEMY) {
                this.velocity.set(0, -10);
                this.alive = false;
            }

        }
    }


    private void moveVertical() {
        PhysicsBody body = Physics.bodyInRect(position.add(0, velocity.y), boxCollider.width, boxCollider.height, standclass);
        if (body != null && alive) {

            float detalY = Mathx.sign(velocity.y);
            float deltaX = Mathx.sign(velocity.x);
            while (Physics.bodyInRect(position.add(0, detalY), boxCollider.width, boxCollider.height, standclass) == null) {
                position.addUp(0, detalY);
            }
            if (velocity.y < 0 && body.getType() == TYPE_BRICK) {
                this.velocity.y = -5;

                body.setActive(false);
            }

            if (body.getType() == TYPE_ENEMY && this.velocity.y > 0) {
                System.out.println(this.velocity);
                this.velocity.set(0, -10);
                body.setActive(false);
            } else if (body.getType() == TYPE_ENEMY) {
                this.velocity.set(0,-15);
                this.alive = false;
            }


            if (body.getType() == TYPE_WATER) {
                this.velocity.set(0, -15);

                this.alive = false;
            }

            if (this.alive && body.getType() != TYPE_ENEMY)  {
                this.velocity.y = 0;
            }


        }

    }

    private void moveSpecial() {
        PhysicsBody body = Physics.bodyInRectD(position.add(0, velocity.y), boxCollider.width, boxCollider.height, InfinityStone.class);
        if (body != null && alive) {

            float detalY = Mathx.sign(velocity.y);
            float deltaX = Mathx.sign(velocity.x);

            if (velocity.y < 0 && this.position.y - 40 < body.getBoxCollider().screenPosition.y
                    && this.position.x + 35 > body.getBoxCollider().screenPosition.x) {
                this.position.y = body.getBoxCollider().screenPosition.y + 30;
                System.out.println(this.position);
                System.out.println(body.getBoxCollider().screenPosition);
                body.setDissabled(false);
                body.setActive(true);
            }

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
        return 0;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
