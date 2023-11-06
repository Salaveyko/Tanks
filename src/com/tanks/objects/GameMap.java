package com.tanks.objects;

import com.tanks.objects.constants.Const;
import com.tanks.objects.constants.Resources;
import com.tanks.objects.piece.Brick;
import com.tanks.objects.piece.Eagle;
import com.tanks.objects.piece.Tank;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameMap implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Brick> bricks;
    private Tank player1;
    private Tank player2;

    public GameMap() {}

    public void loadGameMap(String filePath) {
        GameMap gm;
        try (ObjectInputStream oIn = new ObjectInputStream(
                new FileInputStream(filePath)
        )) {

            gm = (GameMap) oIn.readObject();
            this.bricks = gm.bricks;
            this.player1 = gm.player1;
            this.player2 = gm.player2;

        } catch (Exception e) {
            e.printStackTrace();
            loadDefaultMap();
        }
    }
    private void loadDefaultMap() {
        int xElements = Const.FIELD_WIDTH / Const.BRICK_SIZE; //22
        int yElements = Const.FIELD_HEIGHT / Const.BRICK_SIZE; //12
        Brick[][] arr = new Brick[yElements][xElements];

        int[][] a = {
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3},
                {3, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3},
                {3, 3, 0, 0, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 0, 0, 0, 0, 3},
                {2, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3, 3},
                {3, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3, 2},
                {3, 0, 0, 0, 0, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 0, 0, 0, 3, 3},
                {3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 3},
                {3, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
        };
        int eagles = 0;

        for (int i = 0; i < yElements; i++) {
            for (int j = 0; j < xElements; j++) {

                int x = j * Const.BRICK_SIZE;
                int y = i * Const.BRICK_SIZE;

                if (a[i][j] == 1) {
                    if (getPlayer1() == null) {
                        player1 = new Tank(x + 5, y, Resources.GOLD_TANK_IMG, Direction.RIGHT, Const.LIVES, 0);
                    } else {
                        player2 = new Tank(x + 5, y, Resources.SILVER_TANK_IMG, Direction.LEFT, Const.LIVES, 0);
                    }
                } else if (a[i][j] == 2) {
                    if (eagles == 0) {
                        arr[i][j] = new Eagle(x, y + 6, Resources.GOLDEN_EAGLE, Direction.UP, player1);
                        eagles++;
                    } else {
                        arr[i][j] = new Eagle(x, y + 6, Resources.SILVER_EAGLE, Direction.UP, player2);
                    }
                } else if (a[i][j] == 3) {
                    arr[i][j] = new Brick(x, y, Resources.BRICK, Direction.UP, false);
                } else if (a[i][j] == 4) {
                    arr[i][j] = new Brick(x, y, Resources.IRON_BRICK, Direction.UP, true);
                }
            }
        }

        bricks = Arrays.stream(arr)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void saveGameMap(String filePath) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(
                new FileOutputStream(filePath))
        ) {

            oOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public boolean removeBrick(Brick brick) {
        return bricks.remove(brick);
    }

    public void setPlayer1(Tank player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Tank player2) {
        this.player2 = player2;
    }

    public Tank getPlayer1() {
        return player1;
    }

    public Tank getPlayer2() {
        return player2;
    }

    public void paint(Graphics g) {
        for (Brick br : bricks) {
            br.paint(g);
        }
    }
}
