package game.base.animator;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;

import java.awt.*;

public class BrickAnomator extends GameObject implements Renderer{

    private Animation brickExplosionAnimation;
    //private boolean endOfAction;

    public BrickAnomator() {
        brickExplosionAnimation = new Animation(
                Utils.loadImage("assets/images/brick explosion/explosion1.png"),
                Utils.loadImage("assets/images/brick explosion/explosion2.png"),
                Utils.loadImage("assets/images/brick explosion/explosion3.png"),
                Utils.loadImage("assets/images/brick explosion/explosion4.png"),
                Utils.loadImage("assets/images/brick explosion/explosion5.png"),
                Utils.loadImage("assets/images/brick explosion/explosion6.png")
        );
        this.renderer = brickExplosionAnimation;
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.brickExplosionAnimation.isFinished()) this.isActive = false;

    }

    @Override
    public void render(Graphics g, Vector2D position) {
        if (brickExplosionAnimation != null) {
            brickExplosionAnimation.render(g, position);
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        brickExplosionAnimation.reset();
    }
}
