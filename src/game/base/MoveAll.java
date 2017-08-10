package game.base;

import game.base.physics.BoxCollider;
import game.base.physics.Physics;
import game.base.physics.PhysicsBody;
import game.map.BlueBrick;
import game.map.Brick;
import game.map.InfinityStone;
import game.map.Stone;

/**
 * Created by levua on 8/7/2017.
 */
public class MoveAll {
    Class stand[] = {Brick.class, Stone.class, InfinityStone.class, BlueBrick.class};
    public void moveHorizontal(BoxCollider boxCollider, Vector2D position, Vector2D velocity) {

        PhysicsBody body = Physics.bodyInRect(position.add(0, velocity.y), boxCollider.width, boxCollider.height, stand);
        if (body != null) {
            float detalX = Mathx.sign(velocity.x);
            while (Physics.bodyInRect(position.add(detalX, 0), boxCollider.width, boxCollider.height, stand) == null) {
                position.addUp(detalX, 0);
            }
            velocity.x *= -1;
        }
    }

    public void moveVertical(BoxCollider boxCollider, Vector2D position, Vector2D velocity) {
        PhysicsBody body = Physics.bodyInRect(position.add(0, velocity.y), boxCollider.width, boxCollider.height, stand);
        if (body != null) {
            float detalY = Mathx.sign(velocity.y);
            while (Physics.bodyInRect(position.add(0, detalY), boxCollider.width, boxCollider.height, stand) == null) {
                position.addUp(0, detalY);
            }
            velocity.y = 0;
        }
    }


}
