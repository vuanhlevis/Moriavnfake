package game.enemy;

import game.base.FrameCounter;
import game.base.GameObject;
import game.base.GameObjectPool;
import game.base.Vector2D;
import game.player.Player;

/**
 * Created by levua on 8/9/2017.
 */
public class EnemySpawner extends GameObject {
    Enemy enemy;
    Enemy enemy1;
    Enemy enemy2;
    Enemy enemy3;
    Enemy enemy4;

    public EnemySpawner() {
        super();

        Enemy enemy = GameObjectPool.recycle(Enemy.class);
        enemy.position.set(600,10);

        if (Player.instance.position.x >= 2460) {

            Enemy enemy1 = GameObjectPool.recycle(Enemy.class);
            enemy1.position.set(2470,10);
            enemy1.frameCounter = new FrameCounter(600);
            Enemy enemy2 = GameObjectPool.recycle(Enemy.class);
            enemy2.position.set(2500,10);
            enemy2.frameCounter = new FrameCounter(600);

            Enemy enemy3 = GameObjectPool.recycle(Enemy.class);
            enemy3.position.set(2530,10);
            enemy3.frameCounter = new FrameCounter(600);
            enemy3.velocity.set(-1,0);
            Enemy enemy4 = GameObjectPool.recycle(Enemy.class);
            enemy4.position.set(2560,10);
            enemy4.velocity.set(-1,0);
            enemy4.frameCounter = new FrameCounter(600);
        }

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
//        System.out.println(Player.instance.alive);
        if (!Player.instance.alive) {
            refresh();
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        enemy.isActive = true;
        enemy1.isActive = true;
        enemy2.isActive = true;
        enemy3.isActive = true;
        enemy4.isActive = true;
        enemy.position.set(600,10);
        enemy1.position.set(2470,10);
        enemy2.position.set(2500,10);
        enemy3.position.set(2530,10);
        enemy4.position.set(2560,10);
    }
}
