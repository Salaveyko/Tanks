package com.tanks.map;

import com.tanks.constants.Const;
import com.tanks.constants.Resources;
import com.tanks.objects.Direction;
import com.tanks.objects.piece.Brick;
import com.tanks.objects.piece.Eagle;
import com.tanks.objects.piece.Tank;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapEditor {
    private GameMap map;

    public MapEditor() {
        map = new GameMap();
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public GameMap mapFromArray() {
        int[][] a = new int[0][];
        return mapFromArray(a);
    }

    public GameMap mapFromArray(int[][] mapArr) {
        if (mapArr.length == 0) {
            mapArr = defaultMapArray();
        }

        int xElements = Const.FIELD_WIDTH / Const.BRICK_SIZE; //22
        int yElements = Const.FIELD_HEIGHT / Const.BRICK_SIZE; //12

        Brick[][] bricksArr = new Brick[yElements][xElements];
        Eagle eagle1 = null;
        Eagle eagle2 = null;

        for (int i = 0; i < yElements; i++) {
            for (int j = 0; j < xElements; j++) {

                int x = j * Const.BRICK_SIZE;
                int y = i * Const.BRICK_SIZE;

                if (mapArr[i][j] == 1) {
                    if (map.getPlayer1() == null) {
                        map.setPlayer1(
                                new Tank(x + 5, y, Resources.GOLD_TANK_IMG, Direction.RIGHT, Const.LIVES, 0)
                        );
                    } else {
                        map.setPlayer2(
                                new Tank(x + 5, y, Resources.SILVER_TANK_IMG, Direction.LEFT, Const.LIVES, 0)
                        );
                    }
                } else if (mapArr[i][j] == 2) {
                    if (eagle1 == null) {
                        eagle1 = new Eagle(x, y + 6, Resources.GOLDEN_EAGLE, Direction.UP);
                    } else {
                        eagle2 = new Eagle(x, y + 6, Resources.SILVER_EAGLE, Direction.UP);
                    }
                } else if (mapArr[i][j] == 3) {
                    bricksArr[i][j] = new Brick(x, y, Resources.BRICK, Direction.UP, false);
                } else if (mapArr[i][j] == 4) {
                    bricksArr[i][j] = new Brick(x, y, Resources.IRON_BRICK, Direction.UP, true);
                }
            }
        }

        map.setBricks(
                Arrays.stream(bricksArr)
                        .flatMap(Arrays::stream)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
        if (eagle1 != null) {
            eagle1.setOwner(map.getPlayer1());
            map.addBrick(eagle1);
        }
        if (eagle2 != null) {
            eagle2.setOwner(map.getPlayer2());
            map.addBrick(eagle2);
        }
        map.setName("Stage 1");

        return map;
    }

    private int[][] defaultMapArray() {
        return new int[][]{
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
    }
}
