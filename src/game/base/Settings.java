package game.base;

/**
 * Created by levua on 8/3/2017.
 */
public class Settings {
    public static final int GAMEPLAY_WIDTH = 900;
    public static final int GAMEPLAY_HEIGHT = 460;
    public static final int MAP_HEIGHT = 600;
    public static final int MAP_WIDTH = 6000;
    public static void playBackGround(){
        tklibs.AudioUtils.playMedia("assets/sound/background (1).mp3");
    }

}
