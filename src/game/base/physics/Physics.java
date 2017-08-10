package game.base.physics;

import game.base.Vector2D;

import java.util.Vector;

import static game.map.TileMember.TYPE_INFINITYSTONE;

/**
 * Created by levua on 8/3/2017.
 */
public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();

    public static void reset() {

        for (PhysicsBody body : bodies) {
            if (body.getType() != TYPE_INFINITYSTONE)
                body.setActive(true);
            else body.setActive(false);

            if (body.getStartPosition() != null)
                body.getBoxCollider().screenPosition.set(body.getStartPosition());
        }

    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static <T extends PhysicsBody> T checkPointion(Class<T> classz, int type) {
        for (PhysicsBody body : bodies) {
            if (body.isActive() && body.getClass() == classz && body.getType() == type) {
                return (T) body;
            }
        }
        return null;
    }

    public static <T extends PhysicsBody> T bodyInRect(BoxCollider boxCollider, Class<T> classz) {
        for (PhysicsBody body : bodies) {
            if ((body.isActive() && !body.isDisabled() && body.getBoxCollider().collideWith(boxCollider))) {
                if (body.getClass() == classz) {
                    return (T) body;
                }
            }
        }
        return null;
    }

    public static <T extends PhysicsBody> T bodyInRect(Vector2D position, float width, float height, Class<T>... classz) {
        float left = position.x - width / 2;
        float right = position.x + width / 2;
        float top = position.y - height / 2;
        float bottom = position.y + height / 2;
        for (PhysicsBody body : bodies) {
            for (Class cls : classz) {
                if ((body.isActive() && !body.isDisabled() && body.getBoxCollider().collideWith(top, bottom, left, right))) {
                    if (body.getClass() == cls) {
                        return (T) body;
                    }
                }
            }

        }
        return null;
    }

    public static <T extends PhysicsBody> T bodyInRectD(BoxCollider boxCollider, Class<T> classz) {
        for (PhysicsBody body : bodies) {
            if (!body.isActive() && body.getBoxCollider().collideWith(boxCollider)) {
                if (body.getClass() == classz) {
                    return (T) body;
                }
            }
        }
        return null;
    }

    public static <T extends PhysicsBody> T bodyInRectD(Vector2D position, float width, float height, Class<T> classz) {
        float left = position.x - width / 2;
        float right = position.x + width / 2;
        float top = position.y - height / 2;
        float bottom = position.y + height / 2;

        for (PhysicsBody body : bodies) {
            if (!body.isActive() && body.getBoxCollider().collideWith(top, bottom, left, right)) {
                if (body.getClass() == classz) {
                    return (T) body;
                }
            }
        }
        return null;
    }

    public static void clear() {
        bodies.clear();
    }
}
