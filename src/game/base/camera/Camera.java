package game.base.camera;

import game.base.GameObject;
import game.base.Vector2D;

/**
 * Created by levua on 8/7/2017.
 */
public class Camera extends GameObject {

    public static Camera instance = new Camera();

    private GameObject followedGameObject;

    public void setFollowedGameObject(GameObject followedGameObject) {
        this.followedGameObject = followedGameObject;
    }

    public Camera() {
        super();
        instance = this;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        System.out.println(position);
        super.run(parentPosition);
        if (followedGameObject != null) {
            this.position.x = followedGameObject.screenPosition.x - 200;
        }
        System.out.println(position);
    }

    public Vector2D translate(Vector2D position) {
        return position.substract(this.position);
    }

}
