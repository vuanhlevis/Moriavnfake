package game.base.physics;

/**
 * Created by levua on 8/3/2017.
 */
public interface PhysicsBody {
    boolean isActive();
    boolean isDisabled();
    void setDissabled(boolean disable);
    int getType();
//    boolean isEnemy();
//    boolean isBrick();
//    boolean isStone();
//    boolean isWater();
//    boolean isBlueBrick();
//    boolean isStoneDissapear();
    void setActive(boolean active);



    BoxCollider getBoxCollider();

//    void setActive(boolean active);
}
