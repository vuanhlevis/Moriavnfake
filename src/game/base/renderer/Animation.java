package game.base.renderer;

import game.base.FrameCounter;
import game.base.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by levua on 8/3/2017.
 */
public class Animation implements Renderer{
    private List<BufferedImage> images;
    private int imageIndext;
    private FrameCounter frameCounter;
    private boolean finished;
    private boolean repeat;

    public Animation (int delayFrame, boolean repeat, BufferedImage ... imageArr ) {
        frameCounter = new FrameCounter(delayFrame);
        this.images = Arrays.asList(imageArr);
        this.repeat = repeat;
    }

    public Animation(BufferedImage ... imageArr) {
        this(5,true,imageArr);
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void render(Graphics g, Vector2D position) {
        if (frameCounter.run()) {
            changeIndext();
            frameCounter.reset();
        }

        BufferedImage image = images.get(imageIndext);
        g.drawImage(image,
                (int) position.x - image.getWidth() / 2, (int) position.y - image.getHeight() / 2, null);
    }

    private void changeIndext() {
        if (imageIndext >= images.size() - 1) {
            if (repeat) {
                imageIndext = 0;
            }
            finished = true;
        }
        else {
            imageIndext ++;
        }
    }

    public void reset() {
        imageIndext = 0;
        finished = false;
    }
}
