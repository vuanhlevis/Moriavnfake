package game.base.physics;

import game.base.Vector2D;

/**
 * Created by levua on 8/3/2017.
 */
public interface PhysicsBody {
    boolean isActive();
    boolean isDisabled();
    void setDissabled(boolean disable);
    int getType();
    Vector2D getVelocity();
    Vector2D getStartPosition();
//    boolean isEnemy();
//    boolean isBrick();
//    boolean isStone();
//    boolean isWater();
//    boolean isBlueBrick();
//    boolean isStoneDissapear();
    void setActive(boolean active);



    BoxCollider getBoxCollider();

    Vector2D getPosition();

//    void setActive(boolean active);
}
