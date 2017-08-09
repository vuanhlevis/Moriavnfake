package game.base;

import java.util.Vector;

/**
 * Created by huynq on 7/25/17.
 */
public class GameObjectPool {
    private static Vector<GameObject> pool = new Vector<>();

    public static <T extends GameObject> T recycle(Class<T> classz) {
        for (GameObject gameObject : pool) {
            if (!gameObject.isActive() && gameObject.getClass() == classz) {
                gameObject.refresh();
                return (T) gameObject;
            }
        }

        try {
            T newGameObject = classz.newInstance();
            pool.add(newGameObject);
            GameObject.add(newGameObject);
            return newGameObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void clear() {
        pool.clear();
    }
}
