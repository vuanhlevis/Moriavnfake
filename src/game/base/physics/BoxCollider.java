package game.base.physics;

import game.base.GameObject;
import game.base.Mathx;

import javax.swing.*;

/**
 * Created by levua on 8/3/2017.
 */
public class BoxCollider extends GameObject {
    public float width;
    public float height;

    public BoxCollider(float width, float height) {
        super();
        this.width = width;
        this.height = height;

    }

    public BoxCollider() {
        this(0,0);
    }

    public float left() {
        return this.screenPosition.x - width / 2;
    }

    public float right() {
        return this.screenPosition.x + width / 2;
    }

    public float top() {
        return this.screenPosition.y - height / 2;
    }

    public float bottom() {
        return this.screenPosition.y + height / 2;
    }

    public boolean collideWith(float top, float bottom, float left, float right) {
        boolean xOverlap = Mathx.inRange(left, this.left(), this.right())
                || Mathx.inRange(this.left(), left, right);

        boolean yOverlap = Mathx.inRange(top, this.top(), this.bottom())
                || Mathx.inRange(this.top(), top, bottom);

        return xOverlap && yOverlap;
    }

    public boolean collideWith(BoxCollider other) {
        return collideWith(other.top(), other.bottom(), other.left(), other.right());
    }



    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", position=" + position +
                ", screenPosition=" + screenPosition +
                '}';
    }
}
