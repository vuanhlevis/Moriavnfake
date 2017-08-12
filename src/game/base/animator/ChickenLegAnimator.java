package game.base.animator;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;

import java.awt.*;

/**
 * Created by levua on 8/12/2017.
 */
public class ChickenLegAnimator extends GameObject implements Renderer {

    private Animation legAnimation;
    private Animation currentAnimation;

    public ChickenLegAnimator() {
        legAnimation = new Animation(
                Utils.loadAssetImage("chicken-leg/chickenleg.png"),
                Utils.loadAssetImage("chicken-leg/chickenleg1.png")
        );
    }

//    public void run(ChickenLeg chickenLeg) {
//        currentAnimation = legAnimation;
//    }

    @Override
    public void render(Graphics g, Vector2D position) {
        if (currentAnimation!= null) {
            currentAnimation.render(g,position);
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        currentAnimation.reset();
        legAnimation.reset();
    }
}
