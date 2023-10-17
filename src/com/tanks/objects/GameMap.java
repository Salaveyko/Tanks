package com.tanks.objects;

import com.tanks.objects.constants.Const;
import com.tanks.objects.constants.Images;
import com.tanks.objects.piece.Brick;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameMap {
    private final List<Brick> bricks;

    public GameMap() {
        int xElements = Const.FIELD_WIDTH / Const.BRICK_SIZE;
        int yElements = Const.FIELD_HEIGHT / Const.BRICK_SIZE;
        Brick[][] arr = new Brick[yElements][xElements];

        for (int i = 0; i < yElements; i++) {
            for (int j = 0; j < xElements; j++) {

                arr[i][j] = j != 5 && i != 6 ?
                        null :
                        new Brick(j * Const.BRICK_SIZE, i * Const.BRICK_SIZE, Images.BRICK, Direction.UP, false);
            }
        }
        bricks = Arrays.stream(arr)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public boolean removeBrick(Brick brick){
        return bricks.remove(brick);
    }

    public void paint(Graphics g) {
        for (Brick br : bricks) {
            br.paint(g);
        }
    }
}
