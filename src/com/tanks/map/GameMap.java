package com.tanks.map;

import com.tanks.objects.piece.Brick;
import com.tanks.objects.piece.Tank;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class GameMap implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Brick> bricks;
    private Tank player1;
    private Tank player2;
    private String name;

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public void addBrick(Brick brick){
        bricks.add(brick);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
