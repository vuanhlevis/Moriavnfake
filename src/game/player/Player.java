package game.player;

import game.base.*;
import game.base.actions.WaitAction;
import game.base.animator.BrickAnomator;
import game.base.animator.PlayerAnimator;
import game.base.inputs.InputManager;
import game.base.physics.BoxCollider;
import game.base.physics.Physics;
import game.base.physics.PhysicsBody;
import game.enemy.Enemy;
import game.enemy.LegChicken;
import game.map.Brick;
import game.map.InfinityStone;
import game.map.Stone;
import game.map.Water;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

import static game.map.TileMember.*;

/**
 * Created by levua on 8/3/2017.
 */
public class Player extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    Contraints contraints;
    boolean checkPoint;
    public boolean win;
    public boolean alive;
    Class standclass[] = {Brick.class, Stone.class, InfinityStone.class, Water.class, Enemy.class};
    WaitAction waitAction;
    boolean candown;

    boolean pointion;
    public boolean moveAuto;
    public boolean sleep;
    boolean spawnLeg;

    PlayerAnimator playerAnimator;
    FrameCounter frameCounter;
    FrameCounter deactiveLeg;
    FrameCounter dieCounter;

    public float gravity = 1f;
    public Vector2D velocity;
    public static Player instance = new Player();


    private Clip audio;


    public Player() {
        super();
        this.candown = false;
        this.win = true;
        this.checkPoint = false;
        this.moveAuto = false;
        this.pointion = false;
        this.alive = true;
        this.sleep = false;
        this.spawnLeg = true;

        this.frameCounter = new FrameCounter(50);
        this.dieCounter = new FrameCounter(50);
        this.deactiveLeg = new FrameCounter(5);
        this.velocity = new Vector2D();
        this.playerAnimator = new PlayerAnimator();
        this.renderer = playerAnimator;
        this.boxCollider = new BoxCollider(29, 29);
        this.children.add(boxCollider);
        this.waitAction = new WaitAction(15);
        contraints = new Contraints(0, Settings.GAMEPLAY_HEIGHT + 50, 0, Settings.MAP_WIDTH);
        instance = this;

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
//        System.out.println(win);


        this.velocity.y += gravity;

        this.velocity.x = 0;

        if (spawnLeg && frameCounter.run() && this.position.x == 5727 ) {
            LegChicken legChicken = GameObjectPool.recycle(LegChicken.class);
            legChicken.position.set(5727,300);
            frameCounter.reset();
            if (deactiveLeg.run()) {
                spawnLeg = false;
                win = true;
            }
        }

        if (!pointion && !moveAuto && !sleep) {
            if (InputManager.instance.leftPressed && alive) {
                this.velocity.x -= 5;
            }

            if (InputManager.instance.rightPressed && alive) {
                this.velocity.x += 5;
            }

        } else if (pointion && !moveAuto && !sleep) {
            if (InputManager.instance.leftPressed && alive) {
                this.velocity.x += 5;
            }

            if (InputManager.instance.rightPressed && alive) {
                this.velocity.x -= 5;
            }

            PhysicsBody body = Physics.checkPointion(Brick.class, TYPE_MUSHROOM);
            if (body != null) {
                body.setActive(false);
            }
        }

        if (checkPoint) {
            PhysicsBody body = Physics.checkPointion(Brick.class, TYPE_CHECKPOINT);
            if (body != null) {
                body.setActive(false);
            }
        }


        if (moveAuto && velocity.y <= 0.2 && this.position.x < 5727) {
            this.position.x += 1;
        }

        if (InputManager.instance.upPressed && alive && waitAction.run(this) && !moveAuto && !sleep) {
            if (Physics.bodyInRect(position.add(0, 1), boxCollider.width, boxCollider.height, standclass) != null)
                this.velocity.y = -15;

            waitAction.reset();
        }


        if (candown && InputManager.instance.downPressed && alive) {
            sleep = true;
        }

        waitAction.run(this);

        moveHorizontal();
        this.position.x += velocity.x;

        moveVertical();

        this.position.y += velocity.y;
        moveSpecial();

        this.contraints.make(this.position);
        animate();

        if (this.position.y >= Settings.GAMEPLAY_HEIGHT) this.alive = false;
        if (!this.alive && dieCounter.run()) {
            Physics.reset();
            refresh();
            if (checkPoint) {
                this.position.set(3025, 120);
            }

            dieCounter.reset();
        }


    }

    private void animate() {
        playerAnimator.run(this);
    }

    private void hitEnemy() {

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
                die();
            }

            if (body.getType() == TYPE_FLAG) {
                this.position.x = body.getBoxCollider().screenPosition.x;
                this.moveAuto = true;
                this.gravity = 0.1f;
            }
            if (body.getType() == TYPE_CHECKPOINT) {
                checkPoint = true;
                body.setActive(false);
            }

            if (body.getType() == TYPE_MUSHROOM) {
                pointion = true;
                body.setActive(false);
                //
            }

        }
    }

    private void die() {
        audio = AudioUtils.loadSound("assets/sound/die.wav");
        audio.start();
    }

    private void jump() {
        audio = tklibs.AudioUtils.loadSound("assets/sound/jump.wav");
        audio.start();
    }


    private void moveVertical() {
        PhysicsBody body = Physics.bodyInRect(position.add(0, velocity.y), boxCollider.width, boxCollider.height, standclass);
        if (body != null && body.getType() == TYPE_ELEVATOR) {
            if (body.getType() == TYPE_ELEVATOR && this.position.x >= 3250 && this.position.x < 3320) {
                body.setActive(false);
            }
        }


        if (body != null && alive) {

            float detalY = Mathx.sign(velocity.y);
            float deltaX = Mathx.sign(velocity.x);
            while (Physics.bodyInRect(position.add(0, detalY), boxCollider.width, boxCollider.height, standclass) == null) {
                position.addUp(0, detalY);
            }

            if (body.getType() == TYPE_CHECKPOINT) {
                checkPoint = true;
                body.setActive(false);
            }

            if (this.velocity.y<0 && body.getType() == TYPE_INFINITYSTONE && body.getPosition().x > 2175
                    && body.getPosition().x < 2205 && body.getPosition().y < 360) {
                Enemy enemy = GameObjectPool.recycle(Enemy.class);
                enemy.position.set(body.getPosition().x, body.getPosition().y - 30);

            }

            if ((velocity.y < 0 && body.getType() == TYPE_BRICK && body.getType() != TYPE_CHECKPOINT)) {
                this.velocity.y = -5;

                if (body.getPosition().x < 200 && body.getPosition().y < 240) {
                    Enemy enemy = GameObjectPool.recycle(Enemy.class);

                    enemy.position.set(body.getPosition().x, body.getPosition().y - 30);

                }
                else {
                    BrickAnomator brickAnomator = GameObjectPool.recycle(BrickAnomator.class);
                    brickAnomator.position = body.getBoxCollider().screenPosition;
                    body.setActive(false);

                }

//                if (!this.alive) {
//                    body.setActive(true);
//                }
                jump();
            }

            if (body.getType() == TYPE_ENEMY && this.velocity.y > 0) {
                this.velocity.set(0, -10);
                body.setActive(false);
                die();
            } else if (body.getType() == TYPE_ENEMY) {
                this.velocity.set(0, -15);
                this.alive = false;
                die();
            }

            if (body.getType() == TYPE_MINERALWATER && InputManager.instance.downPressed) {
                sleep = true;
            }

            if (body.getType() == TYPE_NOPLACE) {
                body.setActive(false);
                this.velocity.y = -10;
            }


            if (body.getType() == 36) {
                this.position.x += 80;
                this.position.y -= 50;
            }

            if (body.getType() == TYPE_MUSHROOM) {
                pointion = true;
                body.setActive(false);

            }

            if (body.getType() == TYPE_WATER) {
                this.velocity.set(0, -15);
                this.alive = false;
                die();
            }

            if (body.getType() == TYPE_FLAG) {
                standclass = new Class[] { Stone.class, InfinityStone.class, Water.class, Enemy.class};
                this.position.x += 10;
                this.velocity.y = 2;
            }


//            if (body.getType() == TYPE_BRICK && body.getBoxCollider().screenPosition.x > 1320 && body.getBoxCollider().screenPosition.x < 1530) {
//
//                body.getVelocity().set(0, 10);
//
//            }


            if (this.alive && body.getType() != TYPE_ENEMY) {
                this.velocity.y = 0;
            }

            if (body.getType() == TYPE_TUBE) {
                if (this.position.x >= 4470 && this.position.x <= 4495
                        || (this.position.x >= 4595 && this.position.x <= 4615)
                        || (this.position.x >= 4715 && this.position.x <= 4735))
                    candown = true;
                else candown = false;
            }

        }


    }

    private void moveSpecial() {
        PhysicsBody body = Physics.bodyInRectD(position.add(0, velocity.y), boxCollider.width, boxCollider.height, InfinityStone.class);
        if (body != null && alive) {

            float detalY = Mathx.sign(velocity.y);
            float deltaX = Mathx.sign(velocity.x);

            if (velocity.y < 0 && this.position.y -  body.getBoxCollider().screenPosition.y <= 30
                    && this.position.x + 35 > body.getBoxCollider().screenPosition.x) {
                this.position.y = body.getBoxCollider().screenPosition.y + 30;
                jump();
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

    @Override
    public Vector2D getPosition() {
        return this.position;
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
    public void refresh() {
        super.refresh();
        playerAnimator.refresh();

        this.moveAuto = false;
        this.pointion = false;
        this.sleep = false;
        this.alive = true;
        this.position.set(20, 100);
    }
}
