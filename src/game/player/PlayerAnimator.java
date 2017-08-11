package game.player;

import game.Utils;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;

import java.awt.*;

/**
 * Created by levua on 8/9/2017.
 */
public class PlayerAnimator implements Renderer {

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

    }

    public void run(Player player) {
        if (player.velocity.x < 0) {
            currentAnimation = leftAnimation;

        } else if (player.velocity.x > 0) {
            currentAnimation = rightAnimation;
        } else {
            currentAnimation = straightAnimation;
        }
    }


    @Override
    public void render(Graphics g, Vector2D position) {
        if (currentAnimation!= null) {
            currentAnimation.render(g,position);
        }
    }
}
