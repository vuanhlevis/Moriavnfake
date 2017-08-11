package game.base.animator;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;
import game.player.Player;

import java.awt.*;

/**
 * Created by levua on 8/9/2017.
 */
public class PlayerAnimator extends GameObject implements Renderer {
    private boolean next;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    private Animation straightAnimation;
    private Animation flyLeftAnimation;
    private Animation flyRightAnimation;

    private Animation currentAnimation;

    public PlayerAnimator() {
        this.next = true;
        leftAnimation = new Animation(Utils.loadAssetImage("chicken/left/gaLeft1_32.png"),
                Utils.loadAssetImage("chicken/left/gaLeft2_32.png"),
                Utils.loadAssetImage("chicken/left/gaLeft3_32.png"),
                Utils.loadAssetImage("chicken/left/gaLeft2_32.png")
        );

        rightAnimation = new Animation(Utils.loadAssetImage("chicken/right/gaRight1_32.png"),
                Utils.loadAssetImage("chicken/right/gaRight2_32.png"),
                Utils.loadAssetImage("chicken/right/gaRight3_32.png"),
                Utils.loadAssetImage("chicken/right/gaRight2_32.png")
                );

        straightAnimation = new Animation(
                Utils.loadAssetImage("chicken/gaStraight1_32.png"),
                Utils.loadAssetImage("chicken/gaStraight2_32.png"),
                Utils.loadAssetImage("chicken/gaStraight3_32.png"),
                Utils.loadAssetImage("chicken/gaStraight2_32.png")
        );

        downAnimation = new Animation(Utils.loadAssetImage("chicken/down/gaDown0_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown1_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown2_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown3_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown4_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown5_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown6_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown7_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown8_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown9_32.png"),
                Utils.loadAssetImage("chicken/down/gaDown10_32.png")
        );

        upAnimation = new Animation(
                Utils.loadAssetImage("chicken/up/gaUp1_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp2_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp3_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp4_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp5_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp6_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp7_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp8_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp9_32.png"),
                Utils.loadAssetImage("chicken/up/gaUp10_32.png")

        );

        flyLeftAnimation = new Animation(
                Utils.loadAssetImage("chicken/fly/gaFlyLeft1_32.png"),
                Utils.loadAssetImage("chicken/fly/gaFlyLeft2_32.png")
        );

        flyRightAnimation = new Animation(
                Utils.loadAssetImage("chicken/fly/gaFlyRight1_32.png"),
                Utils.loadAssetImage("chicken/fly/gaFlyRight2_32.png")
        );

    }

    public void run(Player player) {
        if (player.velocity.x < 0 && player.velocity.y == 0 || player.velocity.y == 0.1f) {
            currentAnimation = leftAnimation;

        } else if (player.velocity.x > 0 && player.velocity.y == 0) {
            currentAnimation = rightAnimation;
        } else if (player.sleep) {
            currentAnimation = downAnimation;
            if (currentAnimation.isFinished()) {
                if (player.position.x >= 4470 && player.position.x <= 4495 && next) {
                    player.position.set(405,240);
                    next = false;
                } else if (player.position.x <= 4615 && player.position.x >= 4595 && next) {
                    player.position.set(4725,300);
                    next = false;
                } else if (player.position.x <= 4795 && player.position.x >= 4715 && next) {
                    player.position.set(4485,300);
                    next = false;
                } else if (player.position.x >= 4800 && player.position.x <= 4860 && next) {
                    player.position.set(5025,300);
                }

//                player.position.set(tubes.get(1).position.x - 15, tubes.get(1).position.y - 30);
                currentAnimation = upAnimation;
                if (currentAnimation.isFinished()) {
                    player.sleep = false;
                    next = true;
                    currentAnimation.reset();
                    downAnimation.reset();
                }
            }

        } else if (player.velocity.y != 0 && player.velocity.x > 0) {
            currentAnimation = flyRightAnimation;
        }
        else if (player.velocity.y != 0 && player.velocity.x < 0) {
            currentAnimation = flyLeftAnimation;
        }
        else currentAnimation = straightAnimation;

//        System.out.println(player.velocity);
    }


    @Override
    public void render(Graphics g, Vector2D position) {
        if (currentAnimation != null) {
            currentAnimation.render(g, position);
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        downAnimation.reset();
        upAnimation.reset();
        currentAnimation.reset();
    }
}
