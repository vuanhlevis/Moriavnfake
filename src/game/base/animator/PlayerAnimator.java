package game.base.animator;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;
import game.player.Player;

import java.awt.*;

import static game.map.Tube.tubes;

/**
 * Created by levua on 8/9/2017.
 */
public class PlayerAnimator extends GameObject implements Renderer {

    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    private Animation straightAnimation;

    private Animation currentAnimation;

    public PlayerAnimator() {
        leftAnimation = new Animation(Utils.loadAssetImage("chicken/left/gaLeft1_32.png"),
                Utils.loadAssetImage("chicken/left/gaLeft2_32.png"),
                Utils.loadAssetImage("chicken/left/gaLeft3_32.png")
        );

        rightAnimation = new Animation(Utils.loadAssetImage("chicken/right/gaRight1_32.png"),
                Utils.loadAssetImage("chicken/right/gaRight2_32.png"),
                Utils.loadAssetImage("chicken/right/gaRight3_32.png")
        );

        straightAnimation = new Animation(Utils.loadAssetImage("chicken/gaStraight1_32.png"),
                Utils.loadAssetImage("chicken/gaStraight2_32.png"),
                Utils.loadAssetImage("chicken/gaStraight3_32.png"));

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

    }

    public void run(Player player) {
        if (player.velocity.x < 0) {
            currentAnimation = leftAnimation;

        } else if (player.velocity.x > 0) {
            currentAnimation = rightAnimation;
        } else if (player.sleep){
            currentAnimation = downAnimation;
            if (currentAnimation.isFinished()) {
                player.position.set(tubes.get(1).position.x, tubes.get(1).position.y - 30);
                currentAnimation = upAnimation;
                if (currentAnimation.isFinished()) {
                    player.sleep = false;
                    currentAnimation.reset();
                    downAnimation.reset();
                }
            }

        }
        else currentAnimation = straightAnimation;
    }


    @Override
    public void render(Graphics g, Vector2D position) {
        if (currentAnimation!= null) {
            currentAnimation.render(g,position);
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
