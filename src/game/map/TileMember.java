package game.map;

import game.Utils;
import game.base.GameObject;
import game.base.Vector2D;
import game.base.physics.BoxCollider;
import game.base.physics.PhysicsBody;
import game.base.renderer.ImageRenderer;

public class TileMember extends GameObject implements PhysicsBody {
    private ImageRenderer imageRenderer;
    private BoxCollider boxCollider;


    public static final int TYPE_STONE = 1;
    public static final int TYPE_BRICK = 2;
    public static final int TYPE_INFINITYSTONE = 3;
    public static final int TYPE_WATER = 4;
    public static final int TYPE_BLUEBRICK = 5;
    public static final int TYPE_ENEMY = 6;
    public static final int TYPE_NOPLACE = 7;
    public static final int TYPE_MUSHROOM = 8;
    public static final int TYPE_MINERALWATER = 9;
    public static final int TYPE_FLAG = 10;
    public static final int TYPE_TUBE = 11;
    public static final int TYPE_CHECKPOINT = 12;
    public static final boolean TYPE_SPECIAL = true;
    Vector2D velocity;
    public int type; // 0 => STON, 1 => BRICK, .v.v.v

    public TileMember(int index, int type, Vector2D position) {

        super();
        String path = "assets/tilemap/" + index + ".bmp";

        imageRenderer = new ImageRenderer(Utils.loadImage(path));
        imageRenderer.anchor = new Vector2D(0.5f, 0.5f);
        this.velocity = new Vector2D();


        this.renderer = imageRenderer;
        this.position = position;
        boxCollider = new BoxCollider(30, 30);
        this.children.add(boxCollider);
        this.dissable = false;
//        this.isEnemy = false;
//        this.isBrick = false;
//        this.isStone = false;
//        this.isWater = false;
//        this.isStoneDissapear = false;
//        this.isBlueBrick = false;
    }

    public GameObject createGameObject() {
        //check type = > new Brick()
        // => new
        return null;
        ///
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

    }

//    private boolean isDisabled;

    @Override
    public boolean isDisabled() {
        return dissable;
    }

    @Override
    public void setDissabled(boolean disable) {
        this.dissable = disable;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    public void reset(){
        if (this.dissable == false){
            this.isActive = true;
        }
    }

//    @Override
//    public boolean isEnemy() {
//        return isEnemy;
//    }
//
//    @Override
//    public boolean isBrick() {
//        return isBrick;
//    }
//
//    @Override
//    public boolean isStone() {
//        return isStone;
//    }
//
//    @Override
//    public boolean isWater() {
//        return isWater;
//    }
//
//    @Override
//    public boolean isStoneDissapear() {
//        return isStoneDissapear;
//    }
//
//    @Override
//    public boolean isBlueBrick() {
//        return isBlueBrick;
//    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
