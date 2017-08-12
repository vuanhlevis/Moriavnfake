package game.base.animator;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;
import game.enemy.LegChicken;

import java.awt.*;

/**
 * Created by levua on 8/12/2017.
 */
public class LegChickenAnimator extends GameObject implements Renderer {

    Animation legAnimation;
    Animation currentAnimation;

    public LegChickenAnimator() {
        legAnimation = new Animation(5,false,
                Utils.loadAssetImage("chicken-leg/chickenleg.png"),
                Utils.loadAssetImage("chicken-leg/chickenleg1.png"),
                Utils.loadAssetImage("chicken-leg/chickenleg2.png"),
                Utils.loadAssetImage("chicken-leg/chickenleg3.png"),
                Utils.loadAssetImage("chicken-leg/chickenleg4.png"),
                Utils.loadAssetImage("chicken-leg/chickenleg5.png"));
    }

    public void run(LegChicken legChicken) {
        currentAnimation = legAnimation;
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
        currentAnimation.reset();
        legAnimation.reset();
    }
}
