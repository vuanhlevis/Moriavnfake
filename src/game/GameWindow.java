package game;

import game.base.FrameCounter;
import game.base.GameObject;
import game.base.GameObjectPool;
import game.base.Settings;
import game.base.camera.Camera;
import game.base.inputs.InputManager;
import game.enemy.Enemy;
import game.map.TileMapText;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by levua on 8/3/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;
    float position = Player.instance.position.x;

    InputManager inputManager = InputManager.instance;

    long lastTimeUpdate = -1;

    boolean addEnemy = false;
    boolean stopaddEnemy = false;

    public GameWindow() {
        setupWindow();
        setupBackBuffer();
        setupInputs();
        loadMap();
        addPlayer();
        addEnemy();
        addCamera();
        setupStartupScene();
        this.setVisible(true);
    }

    private void addCamera() {
        Camera camera = new Camera();
        camera.setFollowedGameObject(Player.instance);
        GameObject.add(camera);
    }

    private void addEnemy() {
        Enemy enemy = GameObjectPool.recycle(Enemy.class);
        enemy.position.set(600,10);

//        if (addEnemy) {
//
//            addEnemy = false;
//        }
    }

    private void loadMap() {


        TileMapText tileMapText = new TileMapText("assets/tilemap/testmap.txt",30);
        //GameObject.add(tileMapText);
        tileMapText.draw();
    }

    private void addPlayer() {
        Player player = new Player();
        player.position.set(0 , 100);
        GameObject.add(player);
    }

    private void setupStartupScene() {

    }

    private void setupBackBuffer() {
        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();
    }

    private void setupInputs() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }


    public void loop() {
        while (true) {

            if (Player.instance.position.x >= 2460) {
                addEnemy = true;
            }
//            System.out.println(addEnemy);

            if (addEnemy && !stopaddEnemy) {
                Enemy enemy1 = GameObjectPool.recycle(Enemy.class);
                enemy1.position.set(2470,10);
                enemy1.frameCounter = new FrameCounter(600);
                Enemy enemy2 = GameObjectPool.recycle(Enemy.class);
                enemy2.position.set(2500,10);
                enemy2.frameCounter = new FrameCounter(600);

                Enemy enemy3 = GameObjectPool.recycle(Enemy.class);
                enemy3.position.set(2530,10);
                enemy3.frameCounter = new FrameCounter(600);
                enemy3.velocity.set(-1,0);
                Enemy enemy4 = GameObjectPool.recycle(Enemy.class);
                enemy4.position.set(2560,10);
                enemy4.velocity.set(-1,0);
                enemy4.frameCounter = new FrameCounter(600);
                stopaddEnemy = true;
            }
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }

            long currentTime = System.currentTimeMillis();

            if (currentTime - lastTimeUpdate > 17) {

                lastTimeUpdate = currentTime;

                run();

                render();
            }
        }
    }

    private void run() {
        GameObject.runAll();
        GameObject.runAllActions();
//        SceneManager.instance.changeSceneIfNeeded();
    }

    private void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        GameObject.renderAll(backBufferGraphics2D);

        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(backBufferImage,0,0,null);
    }

    private void setupWindow() {
        this.setSize(Settings.GAMEPLAY_WIDTH, Settings.GAMEPLAY_HEIGHT);
        this.setResizable(false);
        this.setTitle("Platformer begin");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
