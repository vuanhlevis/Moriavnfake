package game.base;

import game.base.actions.Action;
import game.base.physics.Physics;
import game.base.physics.PhysicsBody;
import game.base.renderer.Renderer;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by levua on 8/3/2017.
 */
public class GameObject extends MoveAll {
    public Vector2D position;
    public Vector2D screenPosition;
    public boolean isActive;
    public Renderer renderer;
    public boolean dissable;


    protected Vector<GameObject> children;

    private Vector<Action> actions;
    private Vector<Action> newActions;

    protected static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.isActive = true;
        this.children = new Vector<>();
        this.actions = new Vector<>();
        this.newActions = new Vector<>();
        this.dissable = false;
    }

    public static void add(GameObject gameObject) {
        boolean dupicate = false;
        for (GameObject g : gameObjects) {
            if (g == gameObject)
                dupicate = true;
        }
        if (!dupicate) {
            newGameObjects.add(gameObject);
            if (gameObject instanceof PhysicsBody) {
                Physics.add((PhysicsBody) gameObject);
            }
        }
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && !gameObject.dissable)
                gameObject.run(Vector2D.ZERO);
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive) {
                gameObject.render(g2d);
            }
        }
    }

    public static void runAllActions() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && !gameObject.dissable) {
                gameObject.runActions();
            }
        }
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public void run(Vector2D parentPosition) {
        this.screenPosition = parentPosition.add(position);
        for (GameObject child : children) {
            child.run(this.screenPosition);
        }
    }

    public void runActions() {
        Iterator<Action> iterator = actions.iterator();
        while (iterator.hasNext()) {
            Action action = iterator.next();
            boolean actionDone = action.run(this);
            if (actionDone) {
                iterator.remove();
            }
        }

        actions.addAll(newActions);
        newActions.clear();
    }

    public static void clear() {
        gameObjects.clear();
        GameObjectPool.clear();
        Physics.clear();
    }

    public boolean isActive() {
        return isActive;
    }


    public void addAction(Action action) {
        newActions.add(action);
    }

    public void refresh() {
        isActive = true;
        this.actions.clear();
        this.newActions.clear();
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
