package game.base.animator;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.Animation;
import game.base.renderer.Renderer;
import game.enemy.Enemy;

import java.awt.*;

/**
 * Created by levua on 8/12/2017.
 */
public class EnemyAnimator extends GameObject implements Renderer {

    private Animation enemyAnimation;
    private Animation currenAnimation;

    public EnemyAnimator() {
        enemyAnimation = new Animation(
                Utils.loadImage("assets/images/Alien1.png"),
                Utils.loadImage("assets/images/Alien3.png"),
                Utils.loadImage("assets/images/Alien2.png"));
    }

    public void run(Enemy enemy) {
        currenAnimation = enemyAnimation;
    }

    @Override
    public void render(Graphics g, Vector2D position) {
        if (currenAnimation!= null) {
            currenAnimation.render(g,position);
        }
    }


    @Override
    public void refresh() {
        super.refresh();
        currenAnimation.reset();
        enemyAnimation.reset();
    }
}
