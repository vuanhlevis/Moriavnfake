package game.scenes;

import game.Utils;
import game.base.GameObject;
import game.base.renderer.ImageRenderer;

public class BackGround extends GameObject{
    private ImageRenderer imageRenderer;

    public BackGround() {
        super();

        imageRenderer = new ImageRenderer(Utils.loadImage("assets/images/FinalBackground.png"));
        imageRenderer.anchor.set(0, 1);
        this.renderer = imageRenderer;
    }

    public float getWidth(){
        return this.imageRenderer.getWidth();
    }

    public float getHeight(){
        return this.imageRenderer.getHeight();
    }
}
