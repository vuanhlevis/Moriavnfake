package game.base.camera;

import game.base.GameObject;
import game.base.Vector2D;

/**
 * Created by levua on 8/7/2017.
 */
public class Camera extends GameObject {

    public static Camera instance = new Camera();
    float minFollowPlayer = 0;

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
        super.run(parentPosition);
        if (followedGameObject != null) {
            if (followedGameObject.screenPosition.x - 200 >= 0) {
                this.position.x = followedGameObject.screenPosition.x - 200;

            } else this.position.x = minFollowPlayer;
        }

    }

    public Vector2D translate(Vector2D position) {
        return position.substract(this.position);
    }

}
