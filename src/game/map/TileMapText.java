package game.map;

import game.base.GameObject;
import game.base.Vector2D;
import game.base.renderer.ImageRenderer;

import java.io.BufferedReader;
import java.io.FileReader;

public class TileMapText extends GameObject {

    private ImageRenderer imageRenderer;

    private int x;
    private int y;

    private int tileSize;
    private int[][] map;
    private int mapWidth;
    private int mapHeight;

    //public

    public TileMapText(String path, int tileSize) {
        this.tileSize = tileSize;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());

            map = new int[mapHeight][mapWidth];

            String delimiters = ",";
            for (int row = 0; row < mapHeight; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for (int col = 0; col < mapWidth; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);

                    //test member
//                    if (map[row][col] != 0 &&
//                            map[row][col] != 1 &&
//                            map[row][col] != 2 &&
//                            map[row][col] != 3 &&
//                            map[row][col] != 4 &&
//                            map[row][col] != 5 &&
//                            map[row][col] != 6 &&
//                            map[row][col] != 7 &&
//                            map[row][col] != 27 &&
//                            map[row][col] != 28 &&
//                            map[row][col] != 29 &&
//                            map[row][col] != 36 &&
//                            map[row][col] != 38 &&
//                            map[row][col] != 40 &&
//                            map[row][col] != 41 &&
//                            map[row][col] != 42 &&
//                            map[row][col] != 43 &&
//                            map[row][col] != 44 &&
//                            map[row][col] != 52 &&
//                            map[row][col] != 54 &&
//                            map[row][col] != 55 &&
//                            map[row][col] != 56 &&
//                            map[row][col] != 85 &&
//                            map[row][col] != 86 &&
//                            map[row][col] != 87 &&
//                            map[row][col] != 92 &&
//                            map[row][col] != 93 &&
//                            map[row][col] != 94 &&
//                            map[row][col] != 99 &&
//                            map[row][col] != 105 &&
//                            map[row][col] != 106 &&
//                            map[row][col] != 107 &&
//                            map[row][col] != 112 &&
//                            map[row][col] != 116 &&
//                            map[row][col] != 45 &&
//                            map[row][col] != 135 &&
//                            map[row][col] != 136 &&
//                            map[row][col] != 134 &&
//                            map[row][col] != 100 &&
//                            map[row][col] != 102 &&
//                            map[row][col] != 113 &&
//                            map[row][col] != 114 &&
//                            map[row][col] != 115 &&
//                            map[row][col] != 101 &&
//                            map[row][col] != 99 &&
//                            map[row][col] != 99 &&
//                            map[row][col] != 67 &&
//                            map[row][col] != 99 &&
//                            map[row][col] != 99 &&
//                            map[row][col] != 99 &&
//                            map[row][col] != 31
//                            ) {
//                        System.out.println(map[row][col]);
//                        System.out.println("print missing member");
//                    }


                }
            }
            //System.out.println(Arrays.deepToString(map));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    //col * tileSize, y + row * tileSize
    public void draw() {
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                int tileMem = map[row][col];
                TileMember tileMember = new TileMember(tileMem, TileMember.TYPE_STONE, new Vector2D(col * tileSize, y + row * tileSize));
                if (tileMem == 6 || tileMem == 55) {
                    tileMember = new Brick(tileMem, TileMember.TYPE_BRICK, new Vector2D(col * tileSize, y + row * tileSize));
                } else if (tileMem == 105 || tileMem == 106 || tileMem == 107 ||tileMem == 94 || tileMem == 93 || tileMem == 92) {
                    tileMember = new Water(tileMem, TileMember.TYPE_WATER, new Vector2D(col * tileSize, y + row * tileSize));
                } else if (tileMem == 7) {
                    tileMember = new InfinityStone(tileMem, TileMember.TYPE_INFINITYSTONE, new Vector2D(col * tileSize, y + row * tileSize));
                    tileMember.dissable = true;
                    tileMember.isActive = false;
                    tileMember.getBoxCollider().screenPosition = tileMember.position;

                } else if (tileMem == 38 || tileMem == 45) {
                    tileMember = new Water(tileMem, TileMember.TYPE_WATER, new Vector2D(col * tileSize, y + row * tileSize));
                } else if (tileMem == 29) {
                    tileMember = new Water(tileMem, TileMember.TYPE_STONE, new Vector2D(col * tileSize, y + row * tileSize));
                } else if (tileMem == 31) {
                    tileMember = new BlueBrick(tileMem, TileMember.TYPE_BLUEBRICK, new Vector2D(col * tileSize, y + row * tileSize));
                } else if (tileMem == 52) {
                    tileMember = new Brick(tileMem, TileMember.TYPE_NOPLACE, new Vector2D(col * tileSize, y + row * tileSize));
                } else {
                    tileMember = new Brick(tileMem, TileMember.TYPE_STONE, new Vector2D(col * tileSize, y + row * tileSize));
                }
                if (tileMem != 0) {

                    GameObject.add(tileMember);
                }


                //g.fillRect(x + col * tileSize, y + row * tileSize, tileSize, tileSize);
            }
        }
    }
}
